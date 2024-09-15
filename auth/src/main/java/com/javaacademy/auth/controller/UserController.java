package com.javaacademy.auth.controller;


import com.javaacademy.auth.dto.UserDto;
import com.javaacademy.auth.request.UserRequest;
import com.javaacademy.auth.response.UserResponse;
import com.javaacademy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping
    public UserResponse save(@RequestBody UserRequest userRequest){
        return toResponse(userService.addUser(userRequest.toDto() ));
    }


    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id){
        return toResponse(userService.getUser(id));
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers().stream().map(userDto -> toResponse(userDto)).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable String id , @RequestBody UserRequest userRequest){
        return toResponse(userService.updateUser(id , userRequest.toDto()));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        userService.deleteUser(id);
        return "User deleted successfuly";
    }

    public UserResponse toResponse(UserDto userDto){
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }
}
