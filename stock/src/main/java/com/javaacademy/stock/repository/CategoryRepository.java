package com.javaacademy.stock.repository;


import com.javaacademy.stock.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
