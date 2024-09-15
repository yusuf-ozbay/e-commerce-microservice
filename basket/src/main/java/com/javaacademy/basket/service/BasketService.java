package com.javaacademy.basket.service;

import com.javaacademy.basket.dto.BasketDto;
import com.javaacademy.basket.dto.UserDto;

import java.util.UUID;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);

    BasketDto getBasketByUserId(String userId);

    void removeProductFromBasket(String userId, String basketItemId);

    BasketDto findById(String id);

    UserDto findByUserId(BasketDto basketDto);
}
