package com.citiustech.patient.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void savePatientRecords(Patient patient) {

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

		} else {
			patientRepository.save(patient);
		}
	}

	@Override
	public void deletePatientRecord(Long patientId) {
		Optional<Patient> optPatient = patientRepository.findById(patientId);

		if (optPatient.isPresent()) {
			patientRepository.deleteById(patientId);

			System.out.println("Patient deleted sucessfully " + patientId);
		} else {
			System.out.println("Patient Record not Found..");
		}

	}

	@Override
	public void bookAppointment(AppointmentDTO appointmentD) {

		Appointment appointment = new Appointment();
		Optional<Patient> optPatient = patientRepository.findById(appointmentD.getPatientId());
		

		if (optPatient.isPresent()) {
			String aptId = UUID.randomUUID().toString();
			appointment.setAppointmentId(aptId);
			appointment.setPatientId(appointmentD.getPatientId());
			appointment.setDoctorId(appointmentD.getDoctorId());
			appointment.setAppointmentDate(appointmentD.getAppointmentDate());
			appointmentRepository.save(appointment);

		} else {
			System.out.println("Incorrect details!");
		}
	}

	@Override
	public List<PatientAppointmentDetails> viewAppointment(Long patientId) {

		
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
