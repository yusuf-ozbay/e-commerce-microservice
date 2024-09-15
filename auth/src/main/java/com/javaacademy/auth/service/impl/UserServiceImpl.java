package com.javaacademy.auth.service.impl;

import com.javaacademy.auth.dto.UserDto;
import com.javaacademy.auth.entity.User;
import com.javaacademy.auth.repository.UserRepository;
import com.javaacademy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
   UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = toEntity(userDto);
        user = userRepository.save(user);
        return toDto(user);
    }

    @Override
    public UserDto getUser(String id) {
        return toDto(userRepository.findById(Long.valueOf(id)).get());
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(Long.valueOf(id)).get();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user = userRepository.save(user);
        return toDto(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(Long.valueOf(id)).get();
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .role(user.getRole())
                .build();
    }


    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}
