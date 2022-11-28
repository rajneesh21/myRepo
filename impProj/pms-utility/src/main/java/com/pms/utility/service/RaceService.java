package com.pms.utility.service;

import com.pms.utility.model.Race;
import com.pms.utility.repository.RaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RaceService {
    private static final Logger logger = LoggerFactory.getLogger(RaceService.class);
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getRace(){
        try{
            return raceRepository.findAll();
        }catch (Exception ex){
            logger.error("Error while getting race list", ex);
        }
        return Collections.emptyList();
    }
}
