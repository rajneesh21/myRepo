package com.pms.pmsschedule.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PatientDiagnosisDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientDiagId;
    private Integer patientId;
    private Integer scheduleId;
    private Integer scheduleDtlId;
    private Integer diagnosisId;
    private Integer noteId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isActive;
}
