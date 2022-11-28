package com.pms.utility.repository;


import com.pms.utility.model.Ethnicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthnicityRepository extends JpaRepository<Ethnicity, Integer> {
}
