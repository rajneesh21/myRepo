package com.pms.user.service;

import com.pms.user.model.PatientEmergencyContact;
import com.pms.user.repository.PatientEmergencyContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientEmergencyContactService {

    private final static Logger logger = LoggerFactory.getLogger(PatientEmergencyContactService.class);

    private final PatientEmergencyContactRepository repository;

    public PatientEmergencyContactService(PatientEmergencyContactRepository repository) {
        this.repository = repository;
    }


    public PatientEmergencyContact addPatientEmergencyContact(PatientEmergencyContact emergencyContact){
        try{
            return repository.save(emergencyContact);
        }catch (Exception ex){
            logger.error("Error while saving PatientEmergencyContact !", ex);
            return null;
        }
    }

    public PatientEmergencyContact getPatientEmergencyContact(Integer id){
        try{
            Optional<PatientEmergencyContact> emergencyContact = repository.findByPatientId(id);
            if(emergencyContact.isPresent()){
                return emergencyContact.get();
            }
        }catch (Exception ex){
            logger.error("Error while saving PatientEmergencyContact !", ex);
        }
        return null;
    }
}
