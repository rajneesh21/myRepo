package com.citiustech.doctor.service;

import com.citiustech.doctor.model.Doctor;

public interface DoctorService {

	public Object saveDoctor(Doctor doctor);
	
	public Object deleteDoctor(Long doctorId);
	public Object getDoctorByDoctorId(Long doctorId);
	
	public Object viewAllAppointment(Long doctorId);
}
