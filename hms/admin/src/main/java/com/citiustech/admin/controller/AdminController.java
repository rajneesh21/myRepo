package com.citiustech.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.admin.model.Admin;
import com.citiustech.admin.model.Doctor;
import com.citiustech.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping("/addAdmin")
	public void createAdmin(@RequestBody Admin admin) {
		
		adminService.createAdmin(admin);
	}
	
	@GetMapping("/listAdmin")
	public List<Admin> adminList(){
		
		return adminService.adminList();
	}
	
	@DeleteMapping("/deleteAdmin/{id}")
	public void deleteAdmin(@PathVariable Long id) {
		
		adminService.deleteAdmin(id);
	}
	
	
	@PostMapping("/addDoctor")
	public void createDoctor(@RequestBody Doctor doctor) {
		
		adminService.createDoctor(doctor);
	}
	
	@GetMapping("/listDoctor")
	public List<Doctor> doctorList(){
		
		return adminService.doctorList();
	}
	
	@DeleteMapping("/deleteDoctor/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		
		adminService.deleteDoctor(id);
	}
	
	
}
