package com.pms.pmsschedule.service;

import com.pms.pmsschedule.model.Schedule;
import com.pms.pmsschedule.model.ScheduleDetail;
import com.pms.pmsschedule.repository.ScheduleDetailRepository;
import com.pms.pmsschedule.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class ScheduleDetailService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleDetailService.class);
    private final ScheduleDetailRepository scheduleDetailRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleDetailService(ScheduleDetailRepository scheduleDetailRepository, ScheduleRepository scheduleRepository) {
        this.scheduleDetailRepository = scheduleDetailRepository;
        this.scheduleRepository = scheduleRepository;
    }


    public ScheduleDetail addScheduleDetails(ScheduleDetail scheduleDetail) {
        try {
            Optional<ScheduleDetail> optionalScheduleDetail = scheduleDetailRepository.findByScheduleId(scheduleDetail.getScheduleId());
            if(optionalScheduleDetail.isPresent()){
                scheduleDetail.setScheduleDtlId(optionalScheduleDetail.get().getScheduleDtlId());
                scheduleDetail.setCreatedAt(optionalScheduleDetail.get().getCreatedAt());
            }else{
                scheduleDetail.setCreatedAt(LocalDateTime.now());
            }
            scheduleDetail.setIsActive(1);
            scheduleDetail.setUpdatedAt(LocalDateTime.now());

            return scheduleDetailRepository.save(scheduleDetail);
        } catch (Exception ex) {
            logger.error("Error while saving schedule details !", ex);
        }
        return null;
    }

    public ScheduleDetail getByScheduleId(Integer id){
        try {
            Optional<ScheduleDetail> optionalScheduleDetail = scheduleDetailRepository.findByScheduleId(id);
            if(optionalScheduleDetail.isPresent()){
                return optionalScheduleDetail.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting schedule detail by schedule id!", ex);
        }
        return null;
    }

    public ScheduleDetail getPatientScheduleById(Integer patientId, Integer scheduleId){
        try {
            Optional<ScheduleDetail> optionalScheduleDetail =
                    scheduleDetailRepository.getPatientScheduleDetail(patientId, scheduleId);
            if(optionalScheduleDetail.isPresent()){
                return optionalScheduleDetail.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting patient schedule detail by patient id!", ex);
        }
        return null;
    }

    public Schedule getById(Integer id){
        try{
            Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
            if(optionalSchedule.isPresent()){
                return optionalSchedule.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting schedule info by id !", ex);
        }
        return null;
    }

    public Schedule addAppointment(Schedule schedule){
        try{
            if(schedule.getScheduleId() != null){
                Optional<Schedule> optionalSchedule = scheduleRepository.findById(schedule.getScheduleId());
                if(optionalSchedule.isPresent()){
                    schedule.setCreatedAt(optionalSchedule.get().getCreatedAt());
                    schedule.setCreatedBy(optionalSchedule.get().getCreatedBy());
                    schedule.setStatus(optionalSchedule.get().getStatus());
                    schedule.setDoctorId(optionalSchedule.get().getDoctorId());
                }
            }else{
                ZonedDateTime zonedDateTimeFrom = ZonedDateTime.ofInstant(schedule.getFromTime().toInstant(), ZoneOffset.UTC);
                ZonedDateTime zonedDateTimeTo = ZonedDateTime.ofInstant(schedule.getToTime().toInstant(), ZoneOffset.UTC);
                schedule.setDate(schedule.getDate());
                schedule.setFromTime(zonedDateTimeFrom);
                schedule.setToTime(zonedDateTimeTo);
                schedule.setCreatedAt(LocalDateTime.now());
                schedule.setCreatedBy(schedule.getPatientId());
            }
            schedule.setUpdatedBy(schedule.getPatientId());
            schedule.setUpdatedAt(LocalDateTime.now());
            schedule.setIsActive(1);
            return scheduleRepository.save(schedule);
        }catch (Exception ex){
            logger.error("Error while saving appointment info !", ex);
        }
        return null;
    }

    public Schedule removeAppointment(Integer scheduleId){
        try{
            Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
            if(optionalSchedule.isPresent()){
                optionalSchedule.get().setUpdatedAt(LocalDateTime.now());
                optionalSchedule.get().setIsActive(0);
                return scheduleRepository.save(optionalSchedule.get());
            }
        }catch (Exception ex){
            logger.error("Error while saving appointment info !", ex);
        }
        return null;
    }
}
