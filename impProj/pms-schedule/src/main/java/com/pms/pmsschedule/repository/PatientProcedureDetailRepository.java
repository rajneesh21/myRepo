package com.pms.pmsschedule.repository;

import com.pms.pmsschedule.model.PatientProcedureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientProcedureDetailRepository extends JpaRepository<PatientProcedureDetail, Integer> {
}
