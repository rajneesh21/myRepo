package com.pms.utility.controller;

import com.pms.utility.model.Ethnicity;
import com.pms.utility.model.Race;
import com.pms.utility.service.EthnicityService;
import com.pms.utility.service.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EthnicityController {

    private final EthnicityService ethnicityService;

    public EthnicityController(EthnicityService ethnicityService) {
        this.ethnicityService = ethnicityService;
    }

    @GetMapping("/getAllEthnicity")
    public ResponseEntity<List<Ethnicity>> getAllEthnicity(){
        List<Ethnicity> ethnicity = ethnicityService.getEthnicity();
        return !CollectionUtils.isEmpty(ethnicity) ? ResponseEntity.ok(ethnicity) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
