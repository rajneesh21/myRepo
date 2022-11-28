package com.pms.user.repository;

import com.pms.user.model.Ethnicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthnicityRepository extends JpaRepository<Ethnicity, Integer> {
}
