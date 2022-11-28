package com.citiustech.admin.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.admin.model.Admin;
import com.citiustech.admin.model.Doctor;
import com.citiustech.admin.model.Patient;
import com.citiustech.admin.repository.AdminRepository;
import com.citiustech.admin.repository.DoctorRepository;
import com.citiustech.admin.repository.PatientRepository;
import com.citiustech.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public void createAdmin(Admin admin) {

		Optional<Admin> optAdmin= adminRepository.findById(admin.getId());
		if(optAdmin.isPresent()) {
			Admin adminData=optAdmin.get();
			adminData.setId(admin.getId());
			adminData.setUserName(admin.getUserName());
			adminData.setPassword(admin.getPassword());
			
			adminRepository.save(adminData);
		}
		else {
			adminRepository.save(admin);
		}
	}

	@Override
	public List<Admin> adminList() {

		List<Admin> listAdmin=adminRepository.findAll();
		if(listAdmin.size()>0) {
			return listAdmin;
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteAdmin(Long id) {

		Optional<Admin> optAdmin=adminRepository.findById(id);
		if(optAdmin.isPresent()) {
			adminRepository.deleteById(id);
		}
		else {
			
		}
	}

	@Override
	public void createDoctor(Doctor doctor) {

		Optional<Doctor> optDoct= doctorRepository.findById(doctor.getContactNumber());
		if(optDoct.isPresent()) {
			System.out.println("Doctor Already Present, Please Update it...");
		}
		else {
			doctorRepository.save(doctor);
			
		}
	}

	@Override
	public void updateDoctor(Doctor doctor) {

		Optional<Doctor> optDoct= doctorRepository.findById(doctor.getContactNumber());
		if(optDoct.isPresent()) {
			Doctor doctorData= optDoct.get();
			
			doctorData.setContactNumber(doctor.getContactNumber());
			doctorData.setFirstName(doctor.getFirstName());
			doctorData.setLastName(doctor.getLastName());
			doctorData.setGender(doctor.getGender());
			doctorData.setAddress(doctor.getAddress());
			doctorData.setCity(doctor.getCity());
			doctorData.setQualification(doctor.getQualification());
			doctorData.setSpecialization(doctor.getSpecialization());
			
			doctorRepository.save(doctorData);
		}
	}

	@Override
	public List<Doctor> doctorList() {

		List<Doctor> doctorList= doctorRepository.findAll();
		return doctorList;
	}

	@Override
	public void deleteDoctor(Long id) {

		Optional<Doctor> optDoct= doctorRepository.findById(id);
		if(optDoct.isPresent()) {
			doctorRepository.deleteById(id);
			System.out.println("Doctor deleted successfully...");
		}
		else {
			System.out.println("Doctor is not there..");
		}
	}

	@Override
	public void addPatient(Patient patient) {

		Optional<Patient> optPatient= patientRepository.findById(patient.getContactNumber());
		if(optPatient.isPresent()) {
			System.out.println("Patient Already Present, Please Update it...");
		}
		else {
			patientRepository.save(patient);
			
		}
	}

}
