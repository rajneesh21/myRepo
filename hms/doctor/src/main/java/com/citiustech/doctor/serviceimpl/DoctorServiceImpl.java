package com.citiustech.doctor.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.doctor.client.PatientServiceClient;
import com.citiustech.doctor.model.Doctor;
import com.citiustech.doctor.model.PatientDataModel;
import com.citiustech.doctor.repository.DoctorRepository;
import com.citiustech.doctor.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	/*
	 * @Autowired RestTemplate restTemplate;
	 */

	@Autowired
	PatientServiceClient patientServiceClient;

	@Override
	public ResponseEntity<String> saveDoctor(Doctor doctor) {

		Optional<Doctor> optDoctor = doctorRepository.findById(doctor.getDoctorId());

		if (optDoctor.isPresent()) {
			Doctor doctorData = optDoctor.get();
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
		} else {
			doctorRepository.save(doctor);
			return ResponseEntity.status(HttpStatus.OK).body("Doctor Added Sucessfully");
		}

	}

	@Override
	public ResponseEntity<String> deleteDoctor(Long contactNumber) {

		Optional<Doctor> optDoctor = doctorRepository.findById(contactNumber);
		if (optDoctor.isPresent()) {
			doctorRepository.deleteById(contactNumber);
			return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted sucessfully" + contactNumber);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Doctor Found with this doctorId");
		}
	}

	@Override
	public ResponseEntity getDoctorByDoctorId(Long doctorId) {

		Optional<Doctor> optDoctor = doctorRepository.findById(doctorId);
		if (optDoctor.isPresent()) {
			Doctor doctor = doctorRepository.findById(doctorId).get();
			return ResponseEntity.ok(doctor);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Doctor Found with this doctorId");
		}

	}

	@Override
	public ResponseEntity viewAllAppointment(Long doctorId) {

		Optional<Doctor> optDoctor = doctorRepository.findById(doctorId);

		if (optDoctor.isPresent()) {

			/*
			 * String
			 * patUrl=String.format("http://PATIENT-H/patient/viewAppointmentByDoctorId/%s",
			 * doctorId); List<PatientDataModel> appointmentList =
			 * restTemplate.exchange(patUrl, HttpMethod.GET, null, new
			 * ParameterizedTypeReference<List<PatientDataModel>>() {
			 * 
			 * }).getBody();
			 */
			List<PatientDataModel> appointmentList = patientServiceClient.getPatient(doctorId);

			return ResponseEntity.ok(appointmentList);
		}

		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Id");
		}

	}

}
