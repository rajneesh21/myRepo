package com.pms.user.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PatientEmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer patientEmrId;
    public Integer patientId;
    public String firstName;
    public String lastName;
    public String relation;
    public String email;
    public String contactNumber;
    public String address;

}
