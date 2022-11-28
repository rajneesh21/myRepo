package com.pms.pmsinbox.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    private Integer schedule_id;
    private Integer patient_id;
    private Integer doctor_id;
    private String title;
    private Date date;
    private String from_time;
    private String to_time;
    private String status;
    private String reason;
    private Date created_at;
    private Long created_by;
    private Date updated_at;
    private Long updated_by;
    private Integer is_active;

}
