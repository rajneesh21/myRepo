package com.citiustech.patient.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.citiustech.patient.model.AppointmentBody;
import com.citiustech.patient.model.Patient;
import com.citiustech.patient.model.PatientAndAppointmentDataToDoctor;

public interface PatientService {

	public ResponseEntity<String> bookAppointment(AppointmentBody appointmentD);
	public List<Patient> patientList();
	public ResponseEntity<String> savePatientRecords(Patient patient);
	public ResponseEntity<String> deletePatientRecord(Long patientId);
	public Object viewAppointmentByPatientId(Long patientId);
	public Object viewAppointmentByDoctorId(Long doctorId);
	
}
