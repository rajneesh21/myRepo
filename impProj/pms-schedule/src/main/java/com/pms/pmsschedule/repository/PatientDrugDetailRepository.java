package com.pms.pmsschedule.repository;


import com.pms.pmsschedule.model.PatientDrugDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientDrugDetailRepository extends JpaRepository<PatientDrugDetail, Integer> {
}
