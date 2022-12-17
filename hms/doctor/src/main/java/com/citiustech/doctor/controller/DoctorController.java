package com.citiustech.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Object saveDoctor(@RequestBody Doctor doctor) {
		return doctorService.saveDoctor(doctor);
	}
	
	
	@DeleteMapping("/deleteDoctor/{contactNumber}")
	public Object deleteDoctor(@PathVariable Long contactNumber) {
		
		return doctorService.deleteDoctor(contactNumber);
	}
	
	@GetMapping("/getOneDoctor/{doctorId}")
	public Object getDoctorByDoctorId(@PathVariable Long doctorId) {
		return doctorService.getDoctorByDoctorId(doctorId);
	}
	
	
	@GetMapping("/viewAllAppointment/{doctorId}")
	public Object viewAllAppointment(@PathVariable Long doctorId){
		return doctorService.viewAllAppointment(doctorId);
	}
}
