package com.javaacademy.basket.repository;

import com.javaacademy.basket.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository  extends JpaRepository<Basket, String> {
    Basket findBasketByUserIdAndStatusEquals(String customerId, int status);
    Basket findBasketByBasketId(String basketId);
}
