package com.pms.pmsschedule.controller;


import com.pms.pmsschedule.model.PatientProcedure;
import com.pms.pmsschedule.model.PatientProcedureDetail;
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
public class PatientProcedureController {

    private final PatientProcedureDetailService patientProcedureDetailService;

    @PostMapping("/addPatientProcedureDetail")
    public ResponseEntity<PatientProcedureDetail> addPatientProcedureDetail
            (@RequestBody PatientProcedureDetail patientProcedureDetail){
        PatientProcedureDetail procedureDetail =
                patientProcedureDetailService.addPatientProcedureDetails(patientProcedureDetail);
        return procedureDetail != null ? ResponseEntity.ok(procedureDetail) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/removePatientProcedureDetail/{patientProcDtlId}")
    public ResponseEntity<PatientProcedureDetail> removePatientProcedureDetail
            (@PathVariable Integer patientProcDtlId){
        PatientProcedureDetail procedureDetail =
                patientProcedureDetailService.removePatientProcedureDetails(patientProcDtlId);
        return procedureDetail != null ? ResponseEntity.ok(procedureDetail) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getPatientProcedureDetail/{patientId}/{scheduleId}")
    public ResponseEntity<List<PatientProcedure>> getPatientProcedureDetail(@PathVariable Integer patientId,
                                                                            @PathVariable Integer scheduleId){
        List<PatientProcedure> patientProcedures =
                patientProcedureDetailService.getPatientProcedureDetailById(patientId, scheduleId);
        return !CollectionUtils.isEmpty(patientProcedures) ? ResponseEntity.ok(patientProcedures) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
