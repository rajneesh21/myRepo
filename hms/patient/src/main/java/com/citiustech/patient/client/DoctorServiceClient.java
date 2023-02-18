package com.citiustech.patient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.citiustech.patient.model.DoctorDataModel;

@FeignClient(name="http://DOCTOR-H")
public interface DoctorServiceClient {

	@GetMapping("/doctor/getOneDoctor/{doctorId}")
	public DoctorDataModel getDoctor(@PathVariable Long doctorId);
}
