package com.javaacademy.basket.service;

import com.javaacademy.basket.dto.BasketDto;
import com.javaacademy.basket.dto.UserDto;

public interface BasketService {
    BasketDto saveProductToBasket(BasketDto basketDto);

    BasketDto deleteProduct(String basketId, String productId);

    BasketDto findById(String id);

    UserDto findByUserId(BasketDto basketDto);


}
