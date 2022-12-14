package com.citiustech.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.patient.model.AppointmentDTO;
import com.citiustech.patient.model.Patient;
import com.citiustech.patient.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@GetMapping("/patientList")
	public List<Patient> patientList(){
		
		return patientService.patientList();
	}
	
	@PostMapping("/savePatient")
	public ResponseEntity<String> savePatientRecords(@RequestBody Patient patient) {
		
		return patientService.savePatientRecords(patient);
	}
	
	@DeleteMapping("/deletePatient/{patientId}")
	public ResponseEntity<String> deletePatientRecord(@PathVariable Long patientId) {
		
		return patientService.deletePatientRecord(patientId);
		
	}
	
	@PostMapping("/bookAppointment")
	public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDTO appointmentD) {
		return patientService.bookAppointment(appointmentD);
	}
	
	@GetMapping("/viewAppointment/{patientId}")
	public Object viewAppointment(@PathVariable Long patientId) {
		return patientService.viewAppointment(patientId);
	}
}
