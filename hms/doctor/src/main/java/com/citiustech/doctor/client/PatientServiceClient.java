package com.citiustech.doctor.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.citiustech.doctor.model.PatientDataModel;

@FeignClient(name="http://PATIENT-H")
public interface PatientServiceClient {

	@GetMapping("/patient/viewAppointmentByDoctorId/{doctorId}")
	public List<PatientDataModel> getPatient(@PathVariable Long doctorId);
}
