package com.pms.pmsinbox.Repository;

import com.pms.pmsinbox.model.NurseDetail;
import com.pms.pmsinbox.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NurseDetailRepository extends JpaRepository<NurseDetail, Integer> {
    Optional<NurseDetail> findByNurseId(Integer id);
}
