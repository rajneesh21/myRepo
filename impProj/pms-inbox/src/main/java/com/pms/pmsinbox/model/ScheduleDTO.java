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
public class ScheduleDTO {

    private Integer schedule_id;
    private Users patient;
    private Users doctor;
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
