package com.pms.user.repository;

import com.pms.user.model.PatientEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientEmergencyContactRepository extends JpaRepository<PatientEmergencyContact, Integer> {
    Optional<PatientEmergencyContact> findByPatientId(Integer id);
}
