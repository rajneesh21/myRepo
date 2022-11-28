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
public class PatientProcedure {
    @Id
    private Integer patientProcDtlId;
    private String procedureCode;
    private String procedureDetail;
}
