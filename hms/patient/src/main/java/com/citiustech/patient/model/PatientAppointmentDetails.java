package com.citiustech.patient.model;

public class PatientAppointmentDetails {

	private String appointmentId;
	private String appointmentDate;
	private Long DoctorId;
	private String DoctorName;

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Long getDoctorId() {
		return DoctorId;
	}

	public void setDoctorId(Long doctorId) {
		DoctorId = doctorId;
	}

	public String getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

}
