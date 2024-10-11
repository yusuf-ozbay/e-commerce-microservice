package com.javaacademy.auth.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int role;


}
