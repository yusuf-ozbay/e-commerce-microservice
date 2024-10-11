package com.javaacademy.auth.repository;

import com.javaacademy.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String > {
}
