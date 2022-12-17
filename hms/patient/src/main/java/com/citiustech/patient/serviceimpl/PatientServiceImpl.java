package com.citiustech.patient.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.patient.model.Appointment;
import com.citiustech.patient.model.AppointmentDTO;
import com.citiustech.patient.model.Doctor;
import com.citiustech.patient.model.Patient;
import com.citiustech.patient.model.PatientAppointmentDetails;
import com.citiustech.patient.repository.AppointmentRepository;
import com.citiustech.patient.repository.DoctorRepository;
import com.citiustech.patient.repository.PatientRepository;
import com.citiustech.patient.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public List<Patient> patientList() {

		List<Patient> patients = patientRepository.findAll();

		return patients;
	}

	@Override
	public ResponseEntity<String> savePatientRecords(Patient patient) {
		
		String successMessage="Patient added sucessfully";
		String updateMsg="Patient updated sucessfully";
		
		
		Optional<Patient> optPatient = patientRepository.findById(patient.getPatientId());
		if (optPatient.isPresent()) {

			Patient patientData = optPatient.get();
			patientData.setPatientId(patient.getPatientId());
			patientData.setName(patient.getName());
			patientData.setDob(patient.getDob());
			patientData.setEmail(patient.getEmail());
			patientData.setGender(patient.getGender());
			patientData.setPassword(patient.getPassword());

			patientRepository.save(patientData);
			return ResponseEntity.status(HttpStatus.OK).body(updateMsg);

		} else {
			patientRepository.save(patient);
			return ResponseEntity.status(HttpStatus.OK).body(successMessage);
		}
	}

	@Override
	public ResponseEntity<String> deletePatientRecord(Long patientId) {
		
		String successMessage="Patient deleted sucessfully";
		String errorMsg="Invalid Id, Patient Not Found";
		Optional<Patient> optPatient = patientRepository.findById(patientId);

		if (optPatient.isPresent()) {
			patientRepository.deleteById(patientId);
			return ResponseEntity.status(HttpStatus.OK).body(successMessage);
		
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}

	}

	@Override
	public ResponseEntity<String> bookAppointment(AppointmentDTO appointmentD) {
		String successMessage="Appointment booked sucessfully";
		String errorMsg="Invalid Id, Patient Not Found";
		
		Appointment appointment = new Appointment();
		Optional<Patient> optPatient = patientRepository.findById(appointmentD.getPatientId());
		

		if (optPatient.isPresent()) {
			String aptId = UUID.randomUUID().toString();
			appointment.setAppointmentId(aptId);
			appointment.setPatientId(appointmentD.getPatientId());
			appointment.setDoctorId(appointmentD.getDoctorId());
			appointment.setAppointmentDate(appointmentD.getAppointmentDate());
			appointmentRepository.save(appointment);
			return ResponseEntity.status(HttpStatus.OK).body(successMessage);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
	}

	@Override
	public Object viewAppointment(Long patientId) {

		
		Optional<Patient> optPatient = patientRepository.findById(patientId);
		
		List<PatientAppointmentDetails> patientAppointmentList= new ArrayList<>();

		if (optPatient.isPresent()) {

			List<Appointment> appointmentList = appointmentRepository.findByPatientId(patientId);

			if (appointmentList.size()!=0) {
				for(Appointment appointment:appointmentList) {
					PatientAppointmentDetails patientAppointmentDetails = new PatientAppointmentDetails();
				Doctor doctor = doctorRepository.findById(appointment.getDoctorId()).get();

				patientAppointmentDetails.setAppointmentDate(appointment.getAppointmentDate());
				patientAppointmentDetails.setAppointmentId(appointment.getAppointmentId());
				patientAppointmentDetails.setDoctorId(appointment.getDoctorId());
				patientAppointmentDetails.setDoctorName("Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
				
				patientAppointmentList.add(patientAppointmentDetails);
				patientAppointmentDetails=null;
				
				}
				return patientAppointmentList;
			} else {
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Appointment available");
				

			}

		}

		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Id");

		}
	}
}
