package com.citiustech.patient.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.citiustech.patient.model.AppointmentDTO;
import com.citiustech.patient.model.Patient;

public interface PatientService {

	public ResponseEntity<String> bookAppointment(AppointmentDTO appointmentD);
	public List<Patient> patientList();
	public ResponseEntity<String> savePatientRecords(Patient patient);
	public ResponseEntity<String> deletePatientRecord(Long patientId);
	public Object viewAppointment(Long patientId);
}
