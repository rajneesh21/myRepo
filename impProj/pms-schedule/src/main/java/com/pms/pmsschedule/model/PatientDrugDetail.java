package com.pms.pmsschedule.model;

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
public class PatientDrugDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientDrugDtlId;
    private Integer drugId;
    private Integer scheduleDtlId;
    private Integer patientId;
    private Integer scheduleId;
    private Integer isActive;
}
