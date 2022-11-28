package com.pms.pmsinbox.service;

import com.pms.pmsinbox.Repository.ScheduleRepository;
import com.pms.pmsinbox.Repository.UsersRepository;
import com.pms.pmsinbox.model.Schedule;
import com.pms.pmsinbox.model.ScheduleDTO;
import com.pms.pmsinbox.model.Users;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Data
@Service
public class ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    private final UsersRepository repository;
    private final ScheduleRepository scheduleRepository;

    public Optional<List<ScheduleDTO>> getUpcomingAppointments(Users user) {
        if (user.getRoleId() != 1) {
            if (user.getRoleId() == 3 || user.getRoleId() == 4) {
                List<Schedule> result = repository.findPhysiciansUpcomingSchedule(user.getUserId(), "Cancelled","Visited",ofDays(6));
                List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
                result.stream().forEach(schedule ->{
                    Optional<Users> patient = repository.findById(schedule.getPatient_id());
                    ScheduleDTO scheduleDTO = new ScheduleDTO();
                    scheduleDTO.setSchedule_id(schedule.getSchedule_id());
                    scheduleDTO.setPatient(patient.get());
                    scheduleDTO.setTitle(schedule.getTitle());
                    scheduleDTO.setDate(schedule.getDate());
                    scheduleDTO.setFrom_time(schedule.getFrom_time());
                    scheduleDTO.setTo_time(schedule.getTo_time());
                    scheduleDTO.setStatus(schedule.getStatus());
                    scheduleDTO.setReason(schedule.getReason());
                    scheduleDTO.setCreated_at(schedule.getCreated_at());
                    scheduleDTO.setCreated_by(schedule.getCreated_by());
                    scheduleDTO.setIs_active(schedule.getIs_active());
                    scheduleDTOList.add(scheduleDTO);
                });
                return Optional.of(scheduleDTOList);
//                return Optional.of(u)
            } else {
                List<Schedule> result = repository.findPatientsUpcomingAppointments(user.getUserId(), "Cancelled","Visited",ofDays(6));
                List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
                result.stream().forEach(schedule ->{
                    Optional<Users> doctor = repository.findById(schedule.getDoctor_id());
                    ScheduleDTO scheduleDTO = new ScheduleDTO();
                    scheduleDTO.setSchedule_id(schedule.getSchedule_id());
                    scheduleDTO.setDoctor(doctor.get());
                    scheduleDTO.setTitle(schedule.getTitle());
                    scheduleDTO.setDate(schedule.getDate());
                    scheduleDTO.setFrom_time(schedule.getFrom_time());
                    scheduleDTO.setTo_time(schedule.getTo_time());
                    scheduleDTO.setStatus(schedule.getStatus());
                    scheduleDTO.setReason(schedule.getReason());
                    scheduleDTO.setCreated_at(schedule.getCreated_at());
                    scheduleDTO.setCreated_by(schedule.getCreated_by());
                    scheduleDTO.setIs_active(schedule.getIs_active());
                    scheduleDTOList.add(scheduleDTO);
                });
                return Optional.of(scheduleDTOList);
            }
        }
        return Optional.empty();
    }

    public Optional<List<ScheduleDTO>> getDeclinedAppointments(Users user) {
        if (user.getRoleId() != 1) {
                List<Schedule> result = repository.findPatientsDeclinedAppointments(user.getUserId(), "Cancelled",ofDays(6));
                List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
                result.stream().forEach(schedule ->{
                    Optional<Users> doctor = repository.findById(schedule.getDoctor_id());
                    ScheduleDTO scheduleDTO = new ScheduleDTO();
                    scheduleDTO.setSchedule_id(schedule.getSchedule_id());
                    scheduleDTO.setDoctor(doctor.get());
                    scheduleDTO.setTitle(schedule.getTitle());
                    scheduleDTO.setDate(schedule.getDate());
                    scheduleDTO.setFrom_time(schedule.getFrom_time());
                    scheduleDTO.setTo_time(schedule.getTo_time());
                    scheduleDTO.setStatus(schedule.getStatus());
                    scheduleDTO.setReason(schedule.getReason());
                    scheduleDTO.setCreated_at(schedule.getCreated_at());
                    scheduleDTO.setCreated_by(schedule.getCreated_by());
                    scheduleDTO.setIs_active(schedule.getIs_active());
                    scheduleDTOList.add(scheduleDTO);
                });
                return Optional.of(scheduleDTOList);
        }
        return Optional.empty();
    }


    private Date ofDays(int numOfDays){
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date d =new Date(System.currentTimeMillis() + (numOfDays * DAY_IN_MS));
        return  d;
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

    public Schedule updateStatus(Integer id, String status){
        try{
            Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
            if(optionalSchedule.isPresent()){
                Schedule schedule = optionalSchedule.get();
                schedule.setStatus(status);
                return scheduleRepository.save(schedule);
            }
        }catch (Exception ex){
            logger.error("Error while updating schedule status info by id !", ex);
        }
        return null;
    }
}
