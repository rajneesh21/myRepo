package com.pms.pmsinbox.service;

import com.pms.pmsinbox.Repository.NurseDetailRepository;
import com.pms.pmsinbox.Repository.ScheduleRepository;
import com.pms.pmsinbox.Repository.UsersRepository;
import com.pms.pmsinbox.model.NurseDetail;
import com.pms.pmsinbox.model.Schedule;
import com.pms.pmsinbox.model.ScheduleDTO;
import com.pms.pmsinbox.model.Users;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class NurseDetailService {
    private static final Logger logger = LoggerFactory.getLogger(NurseDetailService.class);
    private final NurseDetailRepository nurseDetailRepository;

    public NurseDetail getById(Integer id){
        try{
            Optional<NurseDetail> nurseDetailOptional = nurseDetailRepository.findByNurseId(id);
            if(nurseDetailOptional.isPresent()){
                return nurseDetailOptional.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting schedule info by id !", ex);
        }
        return null;
    }


}
