package com.pms.pmsinbox.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NurseDetail {

    @Id
    private Integer nurseDtlId;
    private Integer nurseId;
    private Integer doctorId;
    private Integer is_active;

}
