package com.pms.pmsschedule.controller;

import com.pms.pmsschedule.model.PatientDrug;
import com.pms.pmsschedule.model.PatientDrugDetail;
import com.pms.pmsschedule.model.PatientProcedure;
import com.pms.pmsschedule.model.PatientProcedureDetail;
import com.pms.pmsschedule.service.PatientDrugDetailService;
import com.pms.pmsschedule.service.PatientProcedureDetailService;
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
public class PatientDrugController {

    private final PatientDrugDetailService patientDrugDetailService;

    @PostMapping("/addPatientDrugDetail")
    public ResponseEntity<PatientDrugDetail> addPatientDrugDetail
            (@RequestBody PatientDrugDetail patientDrugDetail){
        PatientDrugDetail drugDetail =
                patientDrugDetailService.addPatientDrugDetails(patientDrugDetail);
        return drugDetail != null ? ResponseEntity.ok(drugDetail) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/removePatientDrugDetail/{patientDrugDtlId}")
    public ResponseEntity<PatientDrugDetail> removePatientDrugDetail
            (@PathVariable Integer patientDrugDtlId){
        PatientDrugDetail drugDetail =
                patientDrugDetailService.removePatientDrugDetails(patientDrugDtlId);
        return drugDetail != null ? ResponseEntity.ok(drugDetail) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getPatientDrugDetail/{patientId}/{scheduleId}")
    public ResponseEntity<List<PatientDrug>> getPatientDrugDetail(@PathVariable Integer patientId,
                                                                  @PathVariable Integer scheduleId){
        List<PatientDrug> patientDrug =
                patientDrugDetailService.getPatientDrugDetailById(patientId, scheduleId);
        return !CollectionUtils.isEmpty(patientDrug) ? ResponseEntity.ok(patientDrug) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
