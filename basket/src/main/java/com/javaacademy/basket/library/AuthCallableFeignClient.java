package com.javaacademy.basket.library;


import com.javaacademy.basket.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AuthCallableFeignClient {

     @GetMapping("/users/{id}")
     UserDto getUserById(@PathVariable("id") String id);

}
