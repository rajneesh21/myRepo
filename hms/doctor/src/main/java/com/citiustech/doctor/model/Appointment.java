package com.citiustech.doctor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_details")
public class Appointment {

	@Id
	private String AppointmentId;
	private Long doctorId;
	private Long patientId;
	private String appointmentDate;

	public String getAppointmentId() {
		return AppointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.AppointmentId = appointmentId;
	}

	
	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

}
