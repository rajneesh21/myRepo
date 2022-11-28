package com.citiustech.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.admin.model.Patient;



@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
