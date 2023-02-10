package com.citiustech.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.doctor.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
