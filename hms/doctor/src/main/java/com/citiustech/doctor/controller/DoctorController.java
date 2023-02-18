package com.citiustech.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.doctor.model.Doctor;
import com.citiustech.doctor.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@PostMapping("/addDoctor")
	public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor) {
		return doctorService.saveDoctor(doctor);
	}
	
	
	@DeleteMapping("/deleteDoctor/{contactNumber}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long contactNumber) {
		
		return doctorService.deleteDoctor(contactNumber);
	}
	
	@GetMapping("/getOneDoctor/{doctorId}")
	public ResponseEntity getDoctorByDoctorId(@PathVariable Long doctorId) {
		return doctorService.getDoctorByDoctorId(doctorId);
	}
	
	
	@GetMapping("/viewAllAppointment/{doctorId}")
	public ResponseEntity viewAllAppointment(@PathVariable Long doctorId){
		return doctorService.viewAllAppointment(doctorId);
	}
}
