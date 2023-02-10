package com.citiustech.patient.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.citiustech.patient.model.Appointment;
import com.citiustech.patient.model.AppointmentBody;
import com.citiustech.patient.model.DoctorDataModel;
import com.citiustech.patient.model.Patient;
import com.citiustech.patient.model.PatientAndAppointmentDataToDoctor;
import com.citiustech.patient.model.PatientAppointmentDetails;
import com.citiustech.patient.repository.AppointmentRepository;
import com.citiustech.patient.repository.PatientRepository;
import com.citiustech.patient.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	

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
	public ResponseEntity<String> bookAppointment(AppointmentBody appointmentBody) {
		String successMessage="Appointment booked sucessfully";
		String errorMsg="Invalid Id, Patient Not Found";
		
		Appointment appointment = new Appointment();
		Optional<Patient> optPatient = patientRepository.findById(appointmentBody.getPatientId());
		

		if (optPatient.isPresent()) {
			String aptId = UUID.randomUUID().toString();
			appointment.setAppointmentId(aptId);
			appointment.setPatientId(appointmentBody.getPatientId());
			appointment.setDoctorId(appointmentBody.getDoctorId());
			appointment.setAppointmentDate(appointmentBody.getAppointmentDate());
			appointmentRepository.save(appointment);
			return ResponseEntity.status(HttpStatus.OK).body(successMessage);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
	}

	
		
	@Override
	public Object viewAppointmentByPatientId(Long patientId) {
		
		
		
		Optional<Patient> optPatient = patientRepository.findById(patientId);
		
		List<PatientAppointmentDetails> patientAppointmentList= new ArrayList<>();

		if (optPatient.isPresent()) {

			List<Appointment> appointmentList = appointmentRepository.findByPatientId(patientId);

			if (appointmentList.size()!=0) {
				for(Appointment appointment:appointmentList) {
					PatientAppointmentDetails patientAppointmentDetails = new PatientAppointmentDetails();
					
					String docUrl=String.format("http://DOCTOR-H/doctor/getOneDoctor/%s", appointment.getDoctorId());
				DoctorDataModel doctor = restTemplate.exchange(docUrl, HttpMethod.GET, null, new ParameterizedTypeReference<DoctorDataModel>() {
					
				}).getBody();

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

	@Override
	public Object viewAppointmentByDoctorId(Long doctorId) {

		List<PatientAndAppointmentDataToDoctor> doctorAppointmentList= new ArrayList<>();
		List<Appointment> appointmentList = appointmentRepository.findByDoctorId(doctorId);
		if (appointmentList.size()!=0) {
			for(Appointment appointment:appointmentList) {
				PatientAndAppointmentDataToDoctor patientAndAppointmentDataToDoctor= new PatientAndAppointmentDataToDoctor();
				Patient patient= patientRepository.findById(appointment.getPatientId()).get();
				
				patientAndAppointmentDataToDoctor.setAppointmentId(appointment.getAppointmentId());
				patientAndAppointmentDataToDoctor.setAppointmentDate(appointment.getAppointmentDate());
				patientAndAppointmentDataToDoctor.setPatientId(appointment.getPatientId());
				patientAndAppointmentDataToDoctor.setName(patient.getName());
				patientAndAppointmentDataToDoctor.setGender(patient.getGender());
				patientAndAppointmentDataToDoctor.setDob(patient.getDob());
				patientAndAppointmentDataToDoctor.setEmail(patient.getEmail());
				patientAndAppointmentDataToDoctor.setPassword(patient.getPassword());
				
				doctorAppointmentList.add(patientAndAppointmentDataToDoctor);
				patientAndAppointmentDataToDoctor=null;
				
			}
			return doctorAppointmentList;
		}
		return null;
	}
}

