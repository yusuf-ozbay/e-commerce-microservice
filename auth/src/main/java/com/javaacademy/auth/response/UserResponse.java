package com.javaacademy.auth.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private int role;


}
