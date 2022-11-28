package com.citiustech.patient.service;

import java.util.List;

import com.citiustech.patient.model.Appointment;
import com.citiustech.patient.model.AppointmentDTO;
import com.citiustech.patient.model.Patient;
import com.citiustech.patient.model.PatientAppointmentDetails;

public interface PatientService {

	public void bookAppointment(AppointmentDTO appointmentD);
	public List<Patient> patientList();
	public void savePatientRecords(Patient patient);
	public void deletePatientRecord(Long patientId);
	public List<PatientAppointmentDetails> viewAppointment(Long patientId);
}
