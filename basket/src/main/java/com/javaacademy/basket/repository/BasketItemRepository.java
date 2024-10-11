package com.javaacademy.basket.repository;

import com.javaacademy.basket.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, String> {
    BasketItem findBasketItemByBasket_BasketIdAndProductId(String basketId, String productId);
}
