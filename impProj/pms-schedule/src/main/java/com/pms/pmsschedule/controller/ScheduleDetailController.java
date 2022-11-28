package com.pms.pmsschedule.controller;

import com.pms.pmsschedule.model.Schedule;
import com.pms.pmsschedule.model.ScheduleDetail;
import com.pms.pmsschedule.service.ScheduleDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ScheduleDetailController {

    private final ScheduleDetailService scheduleDetailService;

    public ScheduleDetailController(ScheduleDetailService scheduleDetailService) {
        this.scheduleDetailService = scheduleDetailService;
    }

    @PostMapping("/addSchedule")
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule){
        Schedule appointment = scheduleDetailService.addAppointment(schedule);
        return appointment!= null ? ResponseEntity.ok(appointment) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/removeSchedule/{scheduleId}")
    public ResponseEntity<Schedule> removeSchedule(@PathVariable Integer scheduleId){
        Schedule updateAppointment = scheduleDetailService.removeAppointment(scheduleId);
        return updateAppointment!= null ? ResponseEntity.ok(updateAppointment) :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

    @PostMapping("/addPatientScheduleDetails")
    public ResponseEntity<ScheduleDetail> addPatientScheduleDetails(@RequestBody ScheduleDetail scheduleDetail){
        ScheduleDetail detail = scheduleDetailService.addScheduleDetails(scheduleDetail);
        return detail!= null ? ResponseEntity.ok(detail) :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

    @GetMapping("/getPatientScheduleById/{patientId}/{scheduleId}")
    public ResponseEntity<ScheduleDetail> getPatientScheduleDetails(@PathVariable Integer patientId,
                                                                    @PathVariable Integer scheduleId){
        ScheduleDetail detail = scheduleDetailService.getPatientScheduleById(patientId, scheduleId);
        return detail!= null ? ResponseEntity.ok(detail) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
