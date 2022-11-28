package com.pms.pmsschedule.repository;

import com.pms.pmsschedule.model.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail, Integer> {

    @Query(value = " select sd.* from schedule_detail sd inner join schedule s " +
            "on (sd.schedule_id = s.schedule_id) " +
            "where s.patient_id = ?1 and sd.schedule_id = ?2 ", nativeQuery = true)
    Optional<ScheduleDetail> getPatientScheduleDetail(Integer patientId, Integer ScheduleId);

    Optional<ScheduleDetail> findByScheduleId(Integer id);
}
