package com.citiustech.doctor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.citiustech.doctor.model.Doctor;
import com.citiustech.doctor.model.PatientDataModel;

public interface DoctorService {

	public ResponseEntity<String> saveDoctor(Doctor doctor);
	
	public ResponseEntity<String> deleteDoctor(Long doctorId);
	public ResponseEntity<Doctor> getDoctorByDoctorId(Long doctorId);
	
	public ResponseEntity<List<PatientDataModel>> viewAllAppointment(Long doctorId);
}
