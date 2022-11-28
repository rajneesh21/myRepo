package com.pms.utility.repository;

import com.pms.utility.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
    Optional<Drug> findByDrugName(String name);

    @Query(value = "select distinct(d.drug_name) from drug d ", nativeQuery = true)
    Optional<List<String>> getAllDrugNames();
}
