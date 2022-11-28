package com.pms.user.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Allergy {
    @Id
    private Integer allergyId;
    private Integer patientAllergyId;
    private String allergyCode;
    private String allergyType;
    private String allergyName;
    private String allergySource;
    private String allergyDetail;
}
