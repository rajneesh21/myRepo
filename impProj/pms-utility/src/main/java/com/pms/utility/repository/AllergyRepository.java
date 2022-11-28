package com.pms.utility.repository;

import com.pms.utility.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Integer> {
    Optional<Allergy> findByAllergyCode(String code);
    Optional<List<Allergy>> findByAllergyType(String type);

    @Query(value = "select distinct(a.allergy_type) from allergy a;", nativeQuery = true)
    List<String> getAllergyTypes();

    @Query(value = "select distinct(a.allergy_name) from allergy a;", nativeQuery = true)
    List<String> getAllergyName();

    @Query(value = "select distinct(a.allergy_name) from allergy a where a.allergy_type= ?1 ", nativeQuery = true)
    List<String> getAllergyByType(String type);

    @Query(value = "select distinct(a.allergy_detail) from allergy a where a.allergy_name= ?1 ", nativeQuery = true)
    List<String> getAllergyByName(String name);

    @Query(value = "select * from allergy a where a.allergy_detail= ?1 ", nativeQuery = true)
    Optional<List<Allergy>> getAllergyByDetail(String name);

}
