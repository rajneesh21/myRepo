package com.citiustech.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.doctor.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String>{
//	
//	@Transactional
//    @Query(value ="select * from appointment_details where appointment_id= :id",nativeQuery = true)
//	List<Appointment> findAllappointmenByOneId(@Param("id") String appointmentId);

	List<Appointment> findByDoctorId(Long doctorId);
	List<Appointment> findByPatientId(Long patientId);

}
