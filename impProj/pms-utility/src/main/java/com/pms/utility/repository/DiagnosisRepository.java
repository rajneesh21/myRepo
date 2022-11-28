package com.pms.utility.repository;

import com.pms.utility.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
    Optional<Diagnosis> findByDiagnosisCode(String code);

    @Query(value = "select distinct(d.diagnosis_code) from diagnosis d;", nativeQuery = true)
    Optional<List<String>> getAllDiagnosisCodes();
}
