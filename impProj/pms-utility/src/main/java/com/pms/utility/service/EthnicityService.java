package com.pms.utility.service;

import com.pms.utility.model.Ethnicity;
import com.pms.utility.repository.EthnicityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EthnicityService {
    private static final Logger logger = LoggerFactory.getLogger(EthnicityService.class);
    private final EthnicityRepository ethnicityRepository;

    public EthnicityService(EthnicityRepository ethnicityRepository) {
        this.ethnicityRepository = ethnicityRepository;
    }

    public List<Ethnicity> getEthnicity(){
        try{
            return ethnicityRepository.findAll();
        }catch (Exception ex){
            logger.error("Error while getting ethnicity list", ex);
        }
        return Collections.emptyList();
    }
}
