package com.pms.pmsschedule.service;

import com.pms.pmsschedule.model.PatientDiagnosis;
import com.pms.pmsschedule.model.PatientDiagnosisDetail;
import com.pms.pmsschedule.model.ScheduleDetail;
import com.pms.pmsschedule.repository.PatientDiagnosisDetailRepository;
import com.pms.pmsschedule.repository.PatientDiagnosisRepository;
import com.pms.pmsschedule.repository.ScheduleDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientDiagnosisDetailService {
    private static final Logger logger = LoggerFactory.getLogger(PatientDiagnosisDetailService.class);
    private final PatientDiagnosisRepository patientDiagnosisRepository;
    private final PatientDiagnosisDetailRepository patientDiagnosisDetailRepository;

    public PatientDiagnosisDetailService(PatientDiagnosisRepository patientDiagnosisRepository, PatientDiagnosisDetailRepository patientDiagnosisDetailRepository) {
        this.patientDiagnosisRepository = patientDiagnosisRepository;
        this.patientDiagnosisDetailRepository = patientDiagnosisDetailRepository;
    }


    public PatientDiagnosisDetail addPatientDiagnosisDetails(PatientDiagnosisDetail diagnosisDetail) {
        try {
            if(diagnosisDetail.getPatientDiagId() != null){
                Optional<PatientDiagnosisDetail> detailOptional =
                        patientDiagnosisDetailRepository.findById(diagnosisDetail.getPatientDiagId());
                if(detailOptional.isPresent()){
                    diagnosisDetail.setPatientDiagId(detailOptional.get().getPatientDiagId());
                    diagnosisDetail.setCreatedAt(detailOptional.get().getCreatedAt());
                }
            }else {
                diagnosisDetail.setCreatedAt(LocalDateTime.now());
            }
            diagnosisDetail.setUpdatedAt(LocalDateTime.now());
            diagnosisDetail.setIsActive(1);
            return patientDiagnosisDetailRepository.save(diagnosisDetail);
        } catch (Exception ex) {
            logger.error("Error while saving patient diagnosis details !", ex);
        }
        return null;
    }

    public PatientDiagnosisDetail removePatientDiagnosisDetails(Integer patientDiaId) {
        try {
                Optional<PatientDiagnosisDetail> detailOptional =
                        patientDiagnosisDetailRepository.findById(patientDiaId);
                if(detailOptional.isPresent()){
                    detailOptional.get().setIsActive(0);
                    return patientDiagnosisDetailRepository.save(detailOptional.get());
                }
        } catch (Exception ex) {
            logger.error("Error while removing patient diagnosis details !", ex);
        }
        return null;
    }


    public List<PatientDiagnosis> getPatientDiagnosisDetailById(Integer patientId, Integer scheduleId){
        try {
            Optional<List<PatientDiagnosis>> optionalPatientDiagnosisList =
                    patientDiagnosisRepository.findPatientDiagnosisDetailById(patientId, scheduleId);
            if(optionalPatientDiagnosisList.isPresent()){
                return optionalPatientDiagnosisList.get();
            }
        }catch (Exception ex){
            logger.error("Error while getting patient schedule detail by patient id!", ex);
        }
        return Collections.emptyList();
    }
}
