package com.pms.pmsschedule.repository;


import com.pms.pmsschedule.model.PatientDiagnosisDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientDiagnosisDetailRepository extends JpaRepository<PatientDiagnosisDetail, Integer> {
}
