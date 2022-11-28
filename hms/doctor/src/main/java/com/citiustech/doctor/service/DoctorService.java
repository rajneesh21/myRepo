package com.citiustech.doctor.service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.citiustech.doctor.model.Appointment;
import com.citiustech.doctor.model.Doctor;
import com.citiustech.doctor.model.DoctorAppointmentDetails;
import com.citiustech.doctor.model.Patient;

public interface DoctorService {

	public void saveDoctor(Doctor doctor);
	public List<Doctor> getDoctorDetails();
	public void deleteDoctor(Long doctorId);
	public Doctor getDoctorByDoctorId(Long doctorId);
	public List<Patient> getPatientDetails();
	
	public DoctorAppointmentDetails viewAppointment(Long doctorId);
	public List<DoctorAppointmentDetails> viewAllAppointment(Long doctorId);
}
