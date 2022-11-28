package com.pms.pmsinbox.controller;

import com.pms.pmsinbox.model.NurseDetail;
import com.pms.pmsinbox.model.Schedule;
import com.pms.pmsinbox.model.ScheduleDTO;
import com.pms.pmsinbox.model.Users;
import com.pms.pmsinbox.service.NurseDetailService;
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
public class NurseDetailController {

    private final NurseDetailService nurseDetailService;

    @GetMapping("/getNurseDetail/{id}")
    public ResponseEntity<NurseDetail> getNurseDetail(@PathVariable Integer id) {
        NurseDetail nurseDetail = nurseDetailService.getById(id);
        return nurseDetail!=null ? ResponseEntity.ok(nurseDetail) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
