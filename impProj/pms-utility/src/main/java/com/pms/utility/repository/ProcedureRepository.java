package com.pms.utility.repository;

import com.pms.utility.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    Optional<Procedure> findByProcedureCode(String code);

    @Query(value = "select distinct(p.procedure_code) from \"procedure\" p ", nativeQuery = true)
    Optional<List<String>> getAllProcedureCodes();
}
