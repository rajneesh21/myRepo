package com.pms.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KeyCloakUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
