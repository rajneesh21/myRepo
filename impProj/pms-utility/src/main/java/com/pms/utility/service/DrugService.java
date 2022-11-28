package com.pms.utility.service;

import com.pms.utility.model.Drug;
import com.pms.utility.repository.DrugRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    private final static Logger logger = LoggerFactory.getLogger(DrugService.class);

    private final DrugRepository repository;

    public DrugService(DrugRepository repository) {
        this.repository = repository;
    }


    public Drug getDrugByName(String code){
        try{
            Optional<Drug> allergyCode = repository.findByDrugName(code);
            if(allergyCode.isPresent()){
                return allergyCode.get();
            }
        }catch (Exception ex){
            logger.error("Error while finding drug using code !", ex);
        }
        return null;
    }

    public List<Drug> getAllDrug(){
        try{
            return repository.findAll();
        }catch (Exception ex){
            logger.error("Error while getting drug list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllDrugNames(){
        try{
            Optional<List<String>> optionalStrings = repository.getAllDrugNames();
            if(optionalStrings.isPresent()){
                return optionalStrings.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting drug code list", ex);
        }
        return Collections.emptyList();
    }
}
