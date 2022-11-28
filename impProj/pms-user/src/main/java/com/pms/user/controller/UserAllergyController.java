package com.pms.user.controller;

import com.pms.user.model.Allergy;
import com.pms.user.model.PatientAllergy;
import com.pms.user.service.PatientAllergyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserAllergyController {
    private final PatientAllergyService allergyService;

    public UserAllergyController(PatientAllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @GetMapping("/getPatientAllergy/{id}")
    public ResponseEntity<List<Allergy>> getPatientAllergies(@PathVariable Integer id){
        List<Allergy> patientAllergy = allergyService.getPatientAllergy(id);
        return !CollectionUtils.isEmpty(patientAllergy) ? ResponseEntity.ok(patientAllergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addPatientAllergy")
    public ResponseEntity<PatientAllergy> addPatientAllergy(@RequestBody PatientAllergy allergy){
        PatientAllergy patientAllergy = allergyService.addPatientAllergy(allergy);
        return patientAllergy!= null ? ResponseEntity.ok(patientAllergy) :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

    @DeleteMapping("/removePatientAllergy/{id}")
    public ResponseEntity<PatientAllergy> removePatientAllergy(@PathVariable Integer id){
        PatientAllergy patientAllergy = allergyService.deletePatientAllergy(id);
        return patientAllergy!= null ? ResponseEntity.ok(patientAllergy) :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }
}
