package com.citiustech.doctor.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.doctor.model.Appointment;
import com.citiustech.doctor.model.Doctor;
import com.citiustech.doctor.model.DoctorAppointmentDetails;
import com.citiustech.doctor.model.Patient;
import com.citiustech.doctor.repository.AppointmentRepository;
import com.citiustech.doctor.repository.DoctorRepository;
import com.citiustech.doctor.repository.PatientRepository;
import com.citiustech.doctor.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{
	

	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Override
	public Object saveDoctor(Doctor doctor) {

		Optional<Doctor> optDoctor= doctorRepository.findById(doctor.getDoctorId());
		
		if(optDoctor.isPresent()) {
			Doctor doctorData= optDoctor.get();
			doctorData.setDoctorId(doctor.getDoctorId());
			doctorData.setFirstName(doctor.getFirstName());
			doctorData.setLastName(doctor.getLastName());
			doctorData.setGender(doctor.getGender());
			doctorData.setCity(doctor.getCity());
			doctorData.setQualification(doctor.getQualification());
			doctorData.setSpecialization(doctor.getQualification());
			doctorData.setAddress(doctor.getAddress());
			
			
			doctorRepository.save(doctorData);
			return ResponseEntity.status(HttpStatus.OK).body("Doctor Updated Sucessfully");
		}
		else {
			doctorRepository.save(doctor);
			return ResponseEntity.status(HttpStatus.OK).body("Doctor Added Sucessfully");
		}
		
	}


	@Override
	public Object deleteDoctor(Long contactNumber) {

		Optional<Doctor> optDoctor= doctorRepository.findById(contactNumber);
		if(optDoctor.isPresent()) {
			doctorRepository.deleteById(contactNumber);
			return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted sucessfully"+contactNumber);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Doctor Found with this doctorId");
		}
	}

	@Override
	public Object getDoctorByDoctorId(Long doctorId) {

		Optional<Doctor> optDoctor= doctorRepository.findById(doctorId);
		if(optDoctor.isPresent()) {
			Doctor doctor= doctorRepository.findById(doctorId).get();
			return doctor;
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Doctor Found with this doctorId");
		}
		
	}
	
	
	@Override
	public Object viewAllAppointment(Long doctorId) {
		List<DoctorAppointmentDetails> aptArrList= new ArrayList<>();
		
		Optional<Doctor> optDoctor= doctorRepository.findById(doctorId);
		
		if(optDoctor.isPresent()) {
			List<Appointment> appointments=appointmentRepository.findByDoctorId(doctorId);
			if(appointments.size()!=0) {
				for(Appointment appointment:appointments) {
					Patient patient= patientRepository.findById(appointment.getPatientId()).get();
					DoctorAppointmentDetails doctorAppointmentDetails= new DoctorAppointmentDetails();
					
					doctorAppointmentDetails.setAppointmentId(appointment.getAppointmentId());
					doctorAppointmentDetails.setAppointmentDate(appointment.getAppointmentDate());
					doctorAppointmentDetails.setPatientId(patient.getPatientId());
					doctorAppointmentDetails.setPatientName(patient.getName());
					doctorAppointmentDetails.setDOB(patient.getDob());
					doctorAppointmentDetails.setGender(patient.getGender());
					doctorAppointmentDetails.setEmail(patient.getEmail());
					
					aptArrList.add(doctorAppointmentDetails);
					doctorAppointmentDetails=null;
				}
				return aptArrList;
			}
				else {
		
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Appointment Available");
				}
			}
			else {
				System.out.println("Invalid Id");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Id");
			}
				}
				
	}


