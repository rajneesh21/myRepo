package com.pms.utility.model;

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
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer allergyId;
    private String allergyCode;
    private String allergyType;
    private String allergyName;
    private String allergySource;
    private String allergyDetail;
}
