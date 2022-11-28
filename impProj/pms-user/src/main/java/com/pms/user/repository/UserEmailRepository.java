package com.pms.user.repository;

import com.pms.user.model.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEmailRepository extends JpaRepository<UserEmail,Integer> {
    @Query(value = "select * from public.user_email where auth_code= ?1", nativeQuery = true)
    Optional<UserEmail> findByAuthPasscode(String authPasscode);
}
