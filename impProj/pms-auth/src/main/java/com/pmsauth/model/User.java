package com.pmsauth.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
}
