package com.javaacademy.auth.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long userId;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private int role;


}
