package com.pms.pmsschedule.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PatientDiagnosis {
    @Id
    private Integer patientDiagId;
    private String diagnosisCode;
    private String diagnosisDetail;
}
