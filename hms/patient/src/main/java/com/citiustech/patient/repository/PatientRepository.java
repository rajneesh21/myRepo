package com.citiustech.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

	
}
