package com.pms.utility.controller;

import com.pms.utility.model.Allergy;
import com.pms.utility.model.Diagnosis;
import com.pms.utility.service.AllergyService;
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
public class AllergyController {

    private final AllergyService allergyService;

    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @GetMapping("/getAllergyByCode/{code}")
    public ResponseEntity<Allergy> getAllergyByCode(@PathVariable String code) {
        Allergy allergyByCode = allergyService.getAllergyByCode(code);
        return allergyByCode != null ? ResponseEntity.ok(allergyByCode) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllergyByType/{type}")
    public ResponseEntity<List<Allergy>> getAllergyByType(@PathVariable String type) {
        List<Allergy> allergyByType = allergyService.getAllergyByType(type);
        return !CollectionUtils.isEmpty(allergyByType) ? ResponseEntity.ok(allergyByType) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllergyById/{id}")
    public ResponseEntity<Allergy> getAllergyById(@PathVariable Integer id) {
        Allergy allergyByType = allergyService.getAllergyById(id);
        return allergyByType != null ? ResponseEntity.ok(allergyByType) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllAllergies")
    public ResponseEntity<List<Allergy>> getAllAllergies(){
        List<Allergy> allergy = allergyService.getAllAllergy();
        return !CollectionUtils.isEmpty(allergy) ? ResponseEntity.ok(allergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllAllergyTypes")
    public ResponseEntity<List<String>> getAllAllergiesTypes(){
        List<String> allergy = allergyService.getAllAllergyTypes();
        return !CollectionUtils.isEmpty(allergy) ? ResponseEntity.ok(allergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllAllergyNames")
    public ResponseEntity<List<String>> getAllAllergiesNames(){
        List<String> allergy = allergyService.getAllAllergyName();
        return !CollectionUtils.isEmpty(allergy) ? ResponseEntity.ok(allergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllAllergyByType/{type}")
    public ResponseEntity<List<String>> getAllAllergyByType(@PathVariable String type){
        List<String> allergy = allergyService.getAllAllergyByType(type);
        return !CollectionUtils.isEmpty(allergy) ? ResponseEntity.ok(allergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllAllergyByName/{name}")
    public ResponseEntity<List<String>> getAllAllergyByName(@PathVariable String name){
        List<String> allergy = allergyService.getAllAllergyByName(name);
        return !CollectionUtils.isEmpty(allergy) ? ResponseEntity.ok(allergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAllAllergyByDetail/{detail}")
    public ResponseEntity<List<Allergy>> getAllAllergyByDetail(@PathVariable String detail){
        List<Allergy> allergy = allergyService.getAllAllergyByDetail(detail);
        return !CollectionUtils.isEmpty(allergy) ? ResponseEntity.ok(allergy) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
