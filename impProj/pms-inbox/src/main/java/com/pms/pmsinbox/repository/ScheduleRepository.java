package com.pms.pmsinbox.Repository;

import com.pms.pmsinbox.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository  extends JpaRepository<Schedule, Integer> {
}
