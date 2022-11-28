package com.pmsauth.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Error {
    private String error;
    private String error_description;
}
