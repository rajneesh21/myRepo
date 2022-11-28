package com.pms.utility.controller;

import com.pms.utility.model.Diagnosis;
import com.pms.utility.service.DiagnosisService;
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
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }


    @GetMapping("/getAllDiagnosis")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis(){
        List<Diagnosis> diagnosis = diagnosisService.getAllDiagnosis();
        return !CollectionUtils.isEmpty(diagnosis) ? ResponseEntity.ok(diagnosis) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllDiagnosisCodes")
    public ResponseEntity<List<String>> getAllDiagnosisCodes(){
        List<String> diagnosis = diagnosisService.getAllDiagnosisCodes();
        return !CollectionUtils.isEmpty(diagnosis) ? ResponseEntity.ok(diagnosis) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getDiagnosisByCode/{code}")
    public ResponseEntity<Diagnosis> getDiagnosisByCode(@PathVariable String code){
        Diagnosis diagnosisByCode = diagnosisService.getDiagnosisByCode(code);
        return diagnosisByCode!=null ? ResponseEntity.ok(diagnosisByCode) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
