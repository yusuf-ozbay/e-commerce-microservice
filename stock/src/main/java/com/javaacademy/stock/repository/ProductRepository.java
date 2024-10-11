package com.javaacademy.stock.repository;


import com.javaacademy.stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
}
