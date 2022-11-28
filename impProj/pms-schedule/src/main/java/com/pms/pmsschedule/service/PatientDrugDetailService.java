package com.pms.pmsschedule.service;


import com.pms.pmsschedule.model.PatientDrug;
import com.pms.pmsschedule.model.PatientDrugDetail;
import com.pms.pmsschedule.repository.PatientDrugDetailRepository;
import com.pms.pmsschedule.repository.PatientDrugRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientDrugDetailService {
    private static final Logger logger = LoggerFactory.getLogger(PatientDrugDetailService.class);

    private final PatientDrugRepository patientDrugRepository;
    private final PatientDrugDetailRepository patientDrugDetailRepository;

    public PatientDrugDetailService(PatientDrugRepository patientDrugRepository, PatientDrugDetailRepository patientDrugDetailRepository) {
        this.patientDrugRepository = patientDrugRepository;
        this.patientDrugDetailRepository = patientDrugDetailRepository;
    }


    public PatientDrugDetail addPatientDrugDetails(PatientDrugDetail patientDrugDetail) {
        try {
            if (patientDrugDetail.getPatientDrugDtlId() != null) {
                Optional<PatientDrugDetail> detailOptional =
                        patientDrugDetailRepository.findById(patientDrugDetail.getPatientDrugDtlId());
                detailOptional.ifPresent(drugDetail -> patientDrugDetail.setPatientDrugDtlId(drugDetail.getPatientDrugDtlId()));
            }
            patientDrugDetail.setIsActive(1);
            return patientDrugDetailRepository.save(patientDrugDetail);
        } catch (Exception ex) {
            logger.error("Error while saving patient drug details !", ex);
        }
        return null;
    }

    public PatientDrugDetail removePatientDrugDetails(Integer patientDrugDtlId) {
        try {
            Optional<PatientDrugDetail> detailOptional =
                    patientDrugDetailRepository.findById(patientDrugDtlId);
            if (detailOptional.isPresent()) {
                detailOptional.get().setIsActive(0);
                return patientDrugDetailRepository.save(detailOptional.get());
            }
        } catch (Exception ex) {
            logger.error("Error while removing patient drug details !", ex);
        }
        return null;
    }


    public List<PatientDrug> getPatientDrugDetailById(Integer patientId, Integer scheduleId) {
        try {
            Optional<List<PatientDrug>> optionalPatientDrugList =
                    patientDrugRepository.findPatientDrugDetailById(patientId, scheduleId);
            if (optionalPatientDrugList.isPresent()) {
                return optionalPatientDrugList.get();
            }
        } catch (Exception ex) {
            logger.error("Error while getting patient drug detail by patient id!", ex);
        }
        return Collections.emptyList();
    }
}
