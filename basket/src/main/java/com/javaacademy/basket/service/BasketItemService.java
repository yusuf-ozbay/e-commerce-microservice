package com.javaacademy.basket.service;


import com.javaacademy.basket.entity.BasketItem;

public interface BasketItemService {
    BasketItem findBasketItemByBasketIdAndProductId(String basketItemId , String productId);

    BasketItem save(BasketItem basketItem);

    void delete(BasketItem basketItem);

}
