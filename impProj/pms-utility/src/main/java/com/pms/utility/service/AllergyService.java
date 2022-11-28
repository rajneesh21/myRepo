package com.pms.utility.service;

import com.pms.utility.model.Allergy;
import com.pms.utility.model.Diagnosis;
import com.pms.utility.repository.AllergyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AllergyService {
    private final static Logger logger = LoggerFactory.getLogger(AllergyService.class);

    private final AllergyRepository repository;

    public AllergyService(AllergyRepository repository) {
        this.repository = repository;
    }

    public Allergy getAllergyByCode(String code){
        try{
            Optional<Allergy> allergyCode = repository.findByAllergyCode(code);
            if(allergyCode.isPresent()){
                return allergyCode.get();
            }
        }catch (Exception ex){
            logger.error("Error while finding allergy using code !", ex);
        }
        return null;
    }

    public List<Allergy> getAllergyByType(String type){
        try{
            Optional<List<Allergy>> allergyCode = repository.findByAllergyType(type);
            if(allergyCode.isPresent()){
                return allergyCode.get();
            }
        }catch (Exception ex){
            logger.error("Error while finding allergy using type !", ex);
        }
        return Collections.emptyList();
    }

    public Allergy getAllergyById(Integer id){
        try{
            Optional<Allergy> optionalAllergy = repository.findById(id);
            if(optionalAllergy.isPresent()){
                return optionalAllergy.get();
            }
        }catch (Exception ex){
            logger.error("Error while finding allergy using id !", ex);
        }
        return null;
    }

    public List<Allergy> getAllAllergy(){
        try{
            return repository.findAll();
        }catch (Exception ex){
            logger.error("Error while getting Allergy list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllAllergyTypes(){
        try{
            return repository.getAllergyTypes();
        }catch (Exception ex){
            logger.error("Error while getting Allergy types list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllAllergyName(){
        try{
            return repository.getAllergyName();
        }catch (Exception ex){
            logger.error("Error while getting Allergy names list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllAllergyByType(String code){
        try{
            return repository.getAllergyByType(code);
        }catch (Exception ex){
            logger.error("Error while getting Allergy by type list", ex);
        }
        return Collections.emptyList();
    }

    public List<String> getAllAllergyByName(String name){
        try{
            return repository.getAllergyByName(name);
        }catch (Exception ex){
            logger.error("Error while getting Allergy by name list", ex);
        }
        return Collections.emptyList();
    }

    public List<Allergy> getAllAllergyByDetail(String name){
        try{
            Optional<List<Allergy>> optionalAllergy = repository.getAllergyByDetail(name);
            if(optionalAllergy.isPresent()){
                return optionalAllergy.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting Allergy by detail list", ex);
        }
        return Collections.emptyList();
    }

}
