package com.citiustech.admin.service;

import java.util.List;


import com.citiustech.admin.model.Admin;
import com.citiustech.admin.model.Doctor;
import com.citiustech.admin.model.Patient;


public interface AdminService {

	public void createAdmin(Admin admin);
	public List<Admin> adminList();
	public void deleteAdmin(Long id);
	
	public void createDoctor(Doctor doctor);
	public void updateDoctor(Doctor doctor);
	public List<Doctor> doctorList();
	public void deleteDoctor(Long id);
	
	public void addPatient(Patient patient);
}
