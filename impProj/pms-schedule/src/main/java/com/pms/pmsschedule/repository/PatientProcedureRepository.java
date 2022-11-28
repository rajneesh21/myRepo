package com.pms.pmsschedule.repository;

import com.pms.pmsschedule.model.PatientDiagnosis;
import com.pms.pmsschedule.model.PatientProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientProcedureRepository extends JpaRepository<PatientProcedure, Integer> {

    @Query(value = " select ppd.patient_proc_dtl_id, p.procedure_code, p.procedure_detail" +
            " from patient_procedure_detail ppd inner join \"procedure\" p " +
            "on(ppd.procedure_id = p.procedure_id) where " +
            "ppd.patient_id = ?1 and ppd.schedule_id = ?2 and ppd.is_active = 1", nativeQuery = true)
    Optional<List<PatientProcedure>> findPatientProcedureDetailById(Integer patientId, Integer scheduleId);

}
