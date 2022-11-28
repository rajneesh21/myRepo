package com.pms.pmsschedule.controller;

import com.pms.pmsschedule.model.PatientDiagnosis;
import com.pms.pmsschedule.model.PatientDiagnosisDetail;
import com.pms.pmsschedule.service.PatientDiagnosisDetailService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Data
@RestController
@Slf4j
@CrossOrigin("*")
public class PatientDiagnosisController {

    private final PatientDiagnosisDetailService diagnosisDetailService;

    @PostMapping("/addPatientDiagnosisDetail")
    public ResponseEntity<PatientDiagnosisDetail> addPatientDiagnosisDetail
            (@RequestBody PatientDiagnosisDetail diagnosisDetail){
        PatientDiagnosisDetail patientDiagnosisDetail = diagnosisDetailService.addPatientDiagnosisDetails(diagnosisDetail);
        return patientDiagnosisDetail != null ? ResponseEntity.ok(patientDiagnosisDetail) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getPatientDiagnosisDetail/{patientId}/{scheduleId}")
    public ResponseEntity<List<PatientDiagnosis>> getPatientDiagnosisDetail(@PathVariable Integer patientId,
                                                                    @PathVariable Integer scheduleId){
        List<PatientDiagnosis> patientDiagnoses =
                diagnosisDetailService.getPatientDiagnosisDetailById(patientId, scheduleId);
        return !CollectionUtils.isEmpty(patientDiagnoses) ? ResponseEntity.ok(patientDiagnoses) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/removePatientDiagnosisDetail/{patientDiagId}")
    public ResponseEntity<PatientDiagnosisDetail> removePatientDiagnosisDetail(@PathVariable Integer patientDiagId){
        PatientDiagnosisDetail patientDiagnoses =
                diagnosisDetailService.removePatientDiagnosisDetails(patientDiagId);
        return patientDiagnoses!=null ? ResponseEntity.ok(patientDiagnoses) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
