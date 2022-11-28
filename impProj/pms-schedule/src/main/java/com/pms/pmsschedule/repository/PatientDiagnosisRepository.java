package com.pms.pmsschedule.repository;

import com.pms.pmsschedule.model.PatientDiagnosis;
import com.pms.pmsschedule.model.PatientDiagnosisDetail;
import com.pms.pmsschedule.model.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnosis, Integer> {
    @Query(value = " select pdd.patient_diag_id, d.diagnosis_code, d.diagnosis_detail from " +
            "patient_diagnosis_detail pdd inner join diagnosis d " +
            "on (pdd.diagnosis_id = d.diagnosis_id) where " +
            "pdd.patient_id = ?1 and pdd.schedule_id = ?2 and pdd.is_active = 1", nativeQuery = true)
    Optional<List<PatientDiagnosis>> findPatientDiagnosisDetailById(Integer patientId, Integer scheduleId);
}
