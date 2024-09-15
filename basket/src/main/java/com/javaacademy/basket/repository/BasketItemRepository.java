package com.javaacademy.basket.repository;

import com.javaacademy.basket.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    BasketItem findBasketItemByBasket_BasketIdAndProductId(long basketId, long productId);
}
