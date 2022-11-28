package com.pms.utility.service;


import com.pms.utility.model.Procedure;
import com.pms.utility.repository.ProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class ProcedureService {
    private final static Logger logger = LoggerFactory.getLogger(ProcedureService.class);

    private final ProcedureRepository repository;

    public ProcedureService(ProcedureRepository repository) {
        this.repository = repository;
    }

    public Procedure getProcedureByCode(String code){
        try{
            Optional<Procedure> optionalProcedure = repository.findByProcedureCode(code);
            if(optionalProcedure.isPresent()){
                return optionalProcedure.get();
            }
        }catch (Exception ex){
            logger.error("Error while finding Procedure using code !", ex);
        }
        return null;
    }

    public List<Procedure> getAllProcedure(){
        try{
            return repository.findAll();
        }catch (Exception ex){
            logger.error("Error while getting Procedure list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllProcedureCodes(){
        try{
            Optional<List<String>> optionalStrings = repository.getAllProcedureCodes();
            if(optionalStrings.isPresent()){
                return optionalStrings.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting procedure code list", ex);
        }
        return Collections.emptyList();
    }
}
