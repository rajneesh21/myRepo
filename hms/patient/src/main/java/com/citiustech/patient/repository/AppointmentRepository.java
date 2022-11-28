package com.citiustech.patient.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.patient.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String>{

	List<Appointment> findByPatientId(Long patientId);
	List<Appointment> findByDoctorId(Long doctorId);
}
