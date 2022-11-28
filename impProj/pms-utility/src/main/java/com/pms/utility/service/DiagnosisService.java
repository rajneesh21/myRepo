package com.pms.utility.service;


import com.pms.utility.model.Diagnosis;
import com.pms.utility.repository.DiagnosisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class DiagnosisService {
    private final static Logger logger = LoggerFactory.getLogger(DiagnosisService.class);

    private final DiagnosisRepository repository;

    public DiagnosisService(DiagnosisRepository repository) {
        this.repository = repository;
    }

    public Diagnosis getDiagnosisByCode(String code){
        try{
            Optional<Diagnosis> optionalDiagnosis = repository.findByDiagnosisCode(code);
            if(optionalDiagnosis.isPresent()){
                return optionalDiagnosis.get();
            }
        }catch (Exception ex){
            logger.error("Error while finding diagnosis using code !", ex);
        }
        return null;
    }

    public List<Diagnosis> getAllDiagnosis(){
        try{
            return repository.findAll();
        }catch (Exception ex){
            logger.error("Error while getting diagnosis list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllDiagnosisCodes(){
        try{
            Optional<List<String>> optionalStrings = repository.getAllDiagnosisCodes();
            if(optionalStrings.isPresent()){
                return optionalStrings.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting diagnosis code list", ex);
        }
        return Collections.emptyList();
    }
}
