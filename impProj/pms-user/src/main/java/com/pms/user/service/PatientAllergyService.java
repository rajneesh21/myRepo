package com.pms.user.service;

import com.pms.user.model.Allergy;
import com.pms.user.model.PatientAllergy;
import com.pms.user.repository.AllergyRepository;
import com.pms.user.repository.PatientAllergyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientAllergyService {
    private final static Logger logger = LoggerFactory.getLogger(PatientAllergyService.class);

    private final AllergyRepository allergyRepository;
    private final PatientAllergyRepository patientAllergyRepository;

    public PatientAllergyService(AllergyRepository allergyRepository, PatientAllergyRepository patientAllergyRepository) {
        this.allergyRepository = allergyRepository;
        this.patientAllergyRepository = patientAllergyRepository;

    }

    public List<Allergy> getPatientAllergy(Integer patientId){
        try{
            Optional<List<Allergy>> userAllergy = allergyRepository.getUserAllergy(patientId);
            if(userAllergy.isPresent()){
                return userAllergy.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting patient allergies !",ex);
        }
        return Collections.emptyList();
    }

    public PatientAllergy getPatientAllergyById(Integer patientAllergyId){
        try{
            Optional<PatientAllergy> patientAllergy = patientAllergyRepository.findById(patientAllergyId);
            if(patientAllergy.isPresent()){
                return patientAllergy.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting patient allergies !",ex);
        }
        return null;
    }

    public PatientAllergy addPatientAllergy(PatientAllergy patientAllergy){
        try{
            patientAllergy.setIsActive(1);
            return patientAllergyRepository.save(patientAllergy);
        }catch (Exception ex){
            logger.error("Error while saving patient allergies !",ex);
        }
        return null;
    }

    public PatientAllergy deletePatientAllergy(Integer patientAllergyId){
        try{
            PatientAllergy patientAllergyById = this.getPatientAllergyById(patientAllergyId);
            if(patientAllergyById!=null){
                patientAllergyById.setIsActive(0);
                return patientAllergyRepository.save(patientAllergyById);
            }
        }catch (Exception ex){
            logger.error("Error while saving patient allergies !",ex);
        }
        return null;
    }
}
