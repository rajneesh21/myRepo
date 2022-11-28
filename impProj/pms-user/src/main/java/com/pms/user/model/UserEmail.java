package com.pms.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class UserEmail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userEmailId;
    private String emailSubject;
    private String emailBody;
    private String emailType;
    private String emailSubType;
    private String authCode;
    private Long userId;
    private Integer isEmailSent;
    private LocalDateTime createdAt;
    private Long createdBy;
    private Integer isActive;
}
