package com.pms.user.repository;


import com.pms.user.model.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy, Integer> {
}
