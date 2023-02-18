package com.citiustech.patient.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.citiustech.patient.model.AppointmentBody;
import com.citiustech.patient.model.Patient;
import com.citiustech.patient.model.PatientAndAppointmentDataToDoctor;
import com.citiustech.patient.model.PatientAppointmentDetails;

public interface PatientService {

	public ResponseEntity<String> bookAppointment(AppointmentBody appointmentD);
	public List<Patient> patientList();
	public ResponseEntity<String> savePatientRecords(Patient patient);
	public ResponseEntity<String> deletePatientRecord(Long patientId);
	public ResponseEntity<List<PatientAppointmentDetails>> viewAppointmentByPatientId(Long patientId);
	public ResponseEntity<List<PatientAndAppointmentDataToDoctor>>viewAppointmentByDoctorId(Long doctorId);
	
}
