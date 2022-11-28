package com.pms.pmsschedule.repository;


import com.pms.pmsschedule.model.PatientDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientDrugRepository extends JpaRepository<PatientDrug, Integer> {

    @Query(value = "select pdd.patient_drug_dtl_id, d.drug_name, d.drug_generic_name, " +
            "d.drug_manufacture_name, d.drug_from, d.drug_strength " +
            "from patient_drug_detail pdd inner join drug d " +
            "on(pdd.drug_id = d.drug_id) where pdd.patient_id = ?1 " +
            "and pdd.schedule_id = ?2 and pdd.is_active = 1 ", nativeQuery = true)
    Optional<List<PatientDrug>> findPatientDrugDetailById(Integer patientId, Integer scheduleId);
}
