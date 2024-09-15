package com.javaacademy.auth.request;

import com.javaacademy.auth.dto.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private int role;



    public UserDto toDto(){
        return UserDto.builder()
                .firstname(this.firstname)
                .lastname(this.lastname)
                .password(this.password)
                .email(this.email)
                .role(this.role)
                .build();
    }



}
