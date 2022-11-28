package com.pms.utility.controller;

import com.pms.utility.model.Drug;
import com.pms.utility.service.DrugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }


    @GetMapping("/getDrugByName/{name}")
    public ResponseEntity<Drug> getDrugByName(@PathVariable String name) {
        Drug drug = drugService.getDrugByName(name);
        return drug != null ? ResponseEntity.ok(drug) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllDrugNames")
    public ResponseEntity<List<String>> getAllDrugNames(){
        List<String> drug = drugService.getAllDrugNames();
        return !CollectionUtils.isEmpty(drug) ? ResponseEntity.ok(drug) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllDrugs")
    public ResponseEntity<List<Drug>> getAllDrugs(){
        List<Drug> allDrug = drugService.getAllDrug();
        return !CollectionUtils.isEmpty(allDrug) ? ResponseEntity.ok(allDrug) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
