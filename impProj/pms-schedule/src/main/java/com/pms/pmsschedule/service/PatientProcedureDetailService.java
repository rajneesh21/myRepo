package com.pms.pmsschedule.service;


import com.pms.pmsschedule.model.PatientProcedure;
import com.pms.pmsschedule.model.PatientProcedureDetail;
import com.pms.pmsschedule.repository.PatientProcedureDetailRepository;
import com.pms.pmsschedule.repository.PatientProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientProcedureDetailService {
    private static final Logger logger = LoggerFactory.getLogger(PatientProcedureDetailService.class);

    private final PatientProcedureDetailRepository patientProcedureDetailRepository;
    private final PatientProcedureRepository patientProcedureRepository;

    public PatientProcedureDetailService(PatientProcedureDetailRepository patientProcedureDetailRepository, PatientProcedureRepository patientProcedureRepository) {
        this.patientProcedureDetailRepository = patientProcedureDetailRepository;
        this.patientProcedureRepository = patientProcedureRepository;
    }


    public PatientProcedureDetail addPatientProcedureDetails(PatientProcedureDetail patientProcedureDetail) {
        try {
            if(patientProcedureDetail.getPatientProcDtlId() != null){
                Optional<PatientProcedureDetail> detailOptional =
                        patientProcedureDetailRepository.findById(patientProcedureDetail.getPatientProcDtlId());
                detailOptional.ifPresent(procedureDetail -> patientProcedureDetail.setPatientProcDtlId(procedureDetail.getPatientProcDtlId()));
            }
            patientProcedureDetail.setIsActive(1);
            return patientProcedureDetailRepository.save(patientProcedureDetail);
        } catch (Exception ex) {
            logger.error("Error while saving patient procedure details !", ex);
        }
        return null;
    }

    public PatientProcedureDetail removePatientProcedureDetails(Integer patientProcDtlId) {
        try {
                Optional<PatientProcedureDetail> detailOptional =
                        patientProcedureDetailRepository.findById(patientProcDtlId);
                if(detailOptional.isPresent()){
                    detailOptional.get().setIsActive(0);
                    return patientProcedureDetailRepository.save(detailOptional.get());
                }
        } catch (Exception ex) {
            logger.error("Error while removing patient procedure details !", ex);
        }
        return null;
    }


    public List<PatientProcedure> getPatientProcedureDetailById(Integer patientId, Integer scheduleId){
        try {
            Optional<List<PatientProcedure>> optionalPatientDiagnosisList =
                    patientProcedureRepository.findPatientProcedureDetailById(patientId, scheduleId);
            if(optionalPatientDiagnosisList.isPresent()){
                return optionalPatientDiagnosisList.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting patient procedure detail by patient id!", ex);
        }
        return Collections.emptyList();
    }
}
