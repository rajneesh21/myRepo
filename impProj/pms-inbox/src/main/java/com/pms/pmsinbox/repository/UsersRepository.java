package com.pms.pmsinbox.Repository;

import com.pms.pmsinbox.model.Schedule;
import com.pms.pmsinbox.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UsersRepository extends PagingAndSortingRepository<Users,Integer> {
        Optional<List<Users>> findByRoleId(Integer roleId);

        Optional<Users> findByUserId(Integer userId);

        @Query(value = "FROM Schedule sch  WHERE sch.doctor_id = ?1 AND sch.status !=?2 AND sch.status!=?3 AND sch.date <= ?4 AND sch.date>= now() ORDER BY sch.date")
        List<Schedule> findPhysiciansUpcomingSchedule(Integer doctorId, String approvedStatus,String pendingStatus, Date fromDate);

//        @Query(value = "FROM Schedule sch  WHERE sch.doctor_id = ?1 AND sch.status != ?2 AND sch.date <= ?3")
//        List<Schedule> findPatientsUpcomingAppointments(Long patientId, String status, Date fromDate);

//        @Query(value = "FROM Schedule sch LEFT JOIN Users u ON sch.patient_id=u.user_id\n" +
//                " WHERE sch.patient_id = ?1 AND sch.status = ?2")
        @Query(value = "FROM Schedule sch  WHERE sch.patient_id = ?1 AND sch.status !=?2 AND sch.status!=?3 AND sch.date <= ?4 AND sch.date>= now() ORDER BY sch.date")
        List<Schedule> findPatientsUpcomingAppointments(Integer patientId, String cancelledStatus, String visitedStatus, Date fromDate );

        @Query(value = "FROM Schedule sch  WHERE sch.patient_id = ?1 AND sch.status =?2 AND sch.date <= ?3 AND sch.date>= now() ORDER BY sch.date")
        List<Schedule> findPatientsDeclinedAppointments(Integer patientId, String cancelledStatus,  Date fromDate);

        /*
        @Query("FROM orders ord WHERE ord.created_date  >= :fromDate")
        List<Order> findOrders(Instant fromDate);

        select * from schedule where "patient_Id"=6 and "date" <= now() + interval '6 day' and status='Approved';
        select * from schedule where schedule."doctor_Id"=1 and schedule."date" <= now() + interval '6 day' and status='Approved';
        * select * from schedule where "patientId"=user.getId() and date > now() + 6 and status="active";
        select * from schedule where "patientId"=user.getId() and date > now() + 6 and status="InActive";
        select * from schedule where "doctorId"=user.getId() and date >= now() + interval '6' day and status="active";
        *
        * */


}
