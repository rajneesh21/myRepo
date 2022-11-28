package com.pms.pmsinbox.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String contactNumber;
    private String gender;

    private Integer raceId;
    private Integer ethnicityId;
    private Integer roleId;

    private String address;
    private String designation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer createdBy;
    private Integer updatedBy;

    private Integer isActive;
}
