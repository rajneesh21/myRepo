package com.pms.user.repository;

import com.pms.user.model.UserEmail;
import com.pms.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<UserEmail, Long> {

    @Query(value = "select * from public.userEmail where email= ?1", nativeQuery = true)
    UserEmail saveEmail(UserEmail userEmail);
}
