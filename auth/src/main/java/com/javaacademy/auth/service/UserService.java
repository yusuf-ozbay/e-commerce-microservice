package com.javaacademy.auth.service;

import com.javaacademy.auth.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto userDto);
    UserDto getUser(String id);
    UserDto updateUser(String id , UserDto userDto);
    void deleteUser(String  id);
    List<UserDto> getAllUsers();
}
