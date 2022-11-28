package com.pms.utility.controller;

import com.pms.utility.model.Race;
import com.pms.utility.service.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/getAllRace")
    public ResponseEntity<List<Race>> getAllRace(){
        List<Race> race = raceService.getRace();
        return !CollectionUtils.isEmpty(race) ? ResponseEntity.ok(race) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
