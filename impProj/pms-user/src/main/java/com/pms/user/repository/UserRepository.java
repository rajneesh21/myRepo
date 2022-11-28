package com.pms.user.repository;

import com.pms.user.model.UserEmail;
import com.pms.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
//    @Query(value = "select * from public.users where email= ?1", nativeQuery = true)
//    Users findByEmailAddress(String emailAddress);
    Optional<Users> findByEmail(String emailAddress);

    @Query(value = "select * from public.users where email= ?1", nativeQuery = true)
    Users findByEmailAddress(String emailAddress);

    @Query(value = "select distinct(u.designation) from users u where u.role_id = 3 ", nativeQuery = true)
    List<String> getAllDesignation();

    @Query(value = "select * from users u where designation = ?1 ", nativeQuery = true)
    List<Users> getDoctorByDesignation(String value);
}
