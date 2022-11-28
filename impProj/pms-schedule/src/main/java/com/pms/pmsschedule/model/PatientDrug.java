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
public class PatientDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientDrugDtlId;
    private String drugName;
    private String drugGenericName;
    private String drugManufactureName;
    private String drugFrom;
    private String drugStrength;
}
