package com.javaacademy.basket.service;


import com.javaacademy.basket.entity.BasketItem;

public interface BasketItemService {
    BasketItem findBasketItemByBasketIdAndProductId(long basketItemId , long productId);

    BasketItem save(BasketItem basketItem);

    void delete(long basketItemId);

}
