package com.citiustech.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.admin.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
