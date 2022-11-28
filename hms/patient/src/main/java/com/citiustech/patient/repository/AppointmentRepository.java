package com.citiustech.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.patient.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String>{

	
}
