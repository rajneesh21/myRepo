package com.citiustech.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.doctor.model.Appointment;
import com.citiustech.doctor.model.Doctor;
import com.citiustech.doctor.model.DoctorAppointmentDetails;
import com.citiustech.doctor.model.Patient;
import com.citiustech.doctor.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@PostMapping("/addDoctor")
	public void saveDoctor(@RequestBody Doctor doctor) {
		doctorService.saveDoctor(doctor);
	}
	
	@GetMapping("/getDoctors")
	public List<Doctor> getAllDoctor(){
		return doctorService.getDoctorDetails();
	}
	
	@DeleteMapping("/deleteDoctor/{contactNumber}")
	public void deleteDoctor(@PathVariable Long contactNumber) {
		
		doctorService.deleteDoctor(contactNumber);
	}
	
	@GetMapping("/getOneDoctor/{doctorId}")
	public Doctor getDoctorByDoctorId(@PathVariable Long doctorId) {
		return doctorService.getDoctorByDoctorId(doctorId);
	}
	
	
	@GetMapping("/viewAllAppointment/{doctorId}")
	public List<DoctorAppointmentDetails> viewAllAppointment(@PathVariable Long doctorId){
		return doctorService.viewAllAppointment(doctorId);
	}
}
