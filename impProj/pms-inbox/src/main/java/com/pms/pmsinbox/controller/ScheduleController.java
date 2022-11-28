package com.pms.pmsinbox.controller;

import com.pms.pmsinbox.model.Schedule;
import com.pms.pmsinbox.model.ScheduleDTO;
import com.pms.pmsinbox.model.Users;
import com.pms.pmsinbox.service.ScheduleService;
import com.pms.pmsinbox.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@Slf4j
@CrossOrigin("*")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @GetMapping("/schedules/{id}")
    public List<ScheduleDTO> getUpcomingSchedules(@PathVariable("id") Integer userId) {
        List<ScheduleDTO> scheduleList = null;
        try {
            Optional<Users> usersOptional = userService.getUserById(userId);
            Users user = usersOptional.get();
            scheduleList = scheduleService.getUpcomingAppointments(user).orElse(null);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return scheduleList;
    }

    @GetMapping("/declinedSchedules/{id}")
    public List<ScheduleDTO> getDeclinedSchedules(@PathVariable("id") Integer userId) {
        List<ScheduleDTO> scheduleList = null;
        try {
            Optional<Users> usersOptional = userService.getUserById(userId);
            Users user = usersOptional.get();
            scheduleList = scheduleService.getDeclinedAppointments(user).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return scheduleList;
    }

    @PutMapping("/updateScheduleStatus/{id}/{status}")
    public ResponseEntity<Schedule> updateScheduleStatus(@PathVariable Integer id, @PathVariable String status){
        Schedule schedule = scheduleService.updateStatus(id, status);
        return schedule!= null ? ResponseEntity.ok(schedule) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
