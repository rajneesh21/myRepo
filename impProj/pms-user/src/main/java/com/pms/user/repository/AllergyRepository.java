package com.pms.user.repository;

import com.pms.user.model.Allergy;
import com.pms.user.model.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Integer> {

    @Query(value = "select pa.patient_allergy_id, a.allergy_id, allergy_code, " +
            "allergy_type, allergy_name,allergy_source, allergy_detail from allergy a " +
            "inner join patient_allergy pa " +
            "on (pa.allergy_id = a.allergy_id) " +
            "where pa.patient_id = ?1 and pa.is_active = 1;", nativeQuery = true)
    Optional<List<Allergy>> getUserAllergy(Integer id);
}
