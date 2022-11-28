package com.citiustech.doctor.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.doctor.model.Appointment;
import com.citiustech.doctor.model.AppointmentDTO;
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
	public void saveDoctor(Doctor doctor) {

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
		}
		else {
			doctorRepository.save(doctor);
		}
		
	}

	@Override
	public List<Doctor> getDoctorDetails() {

		List<Doctor> doctorList= doctorRepository.findAll();
		if(doctorList.size()>0) {
			return doctorList;
		}
		else {
			return null;		}
		
		
	}

	@Override
	public void deleteDoctor(Long contactNumber) {

		Optional<Doctor> optDoctor= doctorRepository.findById(contactNumber);
		if(optDoctor.isPresent()) {
			doctorRepository.deleteById(contactNumber);
			System.out.println("Doctor deleted sucessfully"+ contactNumber);
		}
		else {
			System.out.println("No doctor deleted, Doctor Not Found..");
		}
	}

	@Override
	public Doctor getDoctorByDoctorId(Long doctorId) {

		Optional<Doctor> optDoctor= doctorRepository.findById(doctorId);
		if(optDoctor.isPresent()) {
			Doctor doctor= doctorRepository.findById(doctorId).get();
			return doctor;
		}
		else {
			System.out.println("No Doctor Found with this doctorId");
			return null;
		}
		
	}

	@Override
	public List<Patient> getPatientDetails() {
		List<Patient> patientList= patientRepository.findAll();
		if(patientList.size()>0) {
			return patientList;
		}
		else {
			return null;		}
	}
	
	

	@Override
	public DoctorAppointmentDetails viewAppointment(Long doctorId) {
		
		DoctorAppointmentDetails doctorAppointmentDetails= new DoctorAppointmentDetails();
		Optional<Doctor> optDoctor= doctorRepository.findById(doctorId);
		Doctor doctor= optDoctor.get();
		
		if(optDoctor.isPresent()) {
			Appointment appointment=appointmentRepository.findById(doctor.getAppointmentId()).get();
			if(appointment!=null) {
				Patient patient= patientRepository.findById(appointment.getPatientId()).get();
				
				doctorAppointmentDetails.setAppointmentId(patient.getAppointmentId());
				doctorAppointmentDetails.setAppointmentDate(appointment.getAppointmentDate());
				doctorAppointmentDetails.setPatientId(patient.getPatientId());
				doctorAppointmentDetails.setPatientName(patient.getName());
				doctorAppointmentDetails.setDOB(patient.getDob());
				doctorAppointmentDetails.setGender(patient.getGender());
				doctorAppointmentDetails.setEmail(patient.getEmail());
				return doctorAppointmentDetails;
			}
			else {
				System.out.println("No Appointment Available");
				return null;
			}
		}
		else {
			System.out.println("Invalid Id");
			return null;
		}
	}

	@Override
	public List<DoctorAppointmentDetails> viewAllAppointment(Long doctorId) {
		List<DoctorAppointmentDetails> aptArrList= new ArrayList<>();
		DoctorAppointmentDetails doctorAppointmentDetails= new DoctorAppointmentDetails();
		Optional<Doctor> optDoctor= doctorRepository.findById(doctorId);
		Doctor doctor= optDoctor.get();
		
		if(optDoctor.isPresent()) {
			List<Appointment> appointments=appointmentRepository.findAllappointmenByOneId(doctor.getAppointmentId());
			if(appointments.size()!=0) {
				for(Appointment appointment:appointments) {
					Patient patient= patientRepository.findById(appointment.getPatientId()).get();
					
					doctorAppointmentDetails.setAppointmentId(patient.getAppointmentId());
					doctorAppointmentDetails.setAppointmentDate(appointment.getAppointmentDate());
					doctorAppointmentDetails.setPatientId(patient.getPatientId());
					doctorAppointmentDetails.setPatientName(patient.getName());
					doctorAppointmentDetails.setDOB(patient.getDob());
					doctorAppointmentDetails.setGender(patient.getGender());
					doctorAppointmentDetails.setEmail(patient.getEmail());
					
					aptArrList.add(doctorAppointmentDetails);
				}
				return aptArrList;
			}
				else {
					System.out.println("No Appointment Available");
					return null;
				}
			}
			else {
				System.out.println("Invalid Id");
				return null;
			}
				}
				
	}


