package com.pms.utility.controller;

import com.pms.utility.model.Procedure;
import com.pms.utility.service.ProcedureService;
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
public class ProcedureController {

    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/getAllProcedure")
    public ResponseEntity<List<Procedure>> getAllProcedure(){
        List<Procedure> procedure = procedureService.getAllProcedure();
        return !CollectionUtils.isEmpty(procedure) ? ResponseEntity.ok(procedure) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllProcedureCodes")
    public ResponseEntity<List<String>> getAllProcedureCodes(){
        List<String> procedure = procedureService.getAllProcedureCodes();
        return !CollectionUtils.isEmpty(procedure) ? ResponseEntity.ok(procedure) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getProcedureByCode/{code}")
    public ResponseEntity<Procedure> getProcedureByCode(@PathVariable String code){
        Procedure procedureByCode = procedureService.getProcedureByCode(code);
        return procedureByCode!=null ? ResponseEntity.ok(procedureByCode) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
