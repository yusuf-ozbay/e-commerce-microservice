package com.javaacademy.basket.service.impl;

import com.javaacademy.basket.dto.BasketItemDto;
import com.javaacademy.basket.dto.ProductDto;
import com.javaacademy.basket.entity.BasketItem;
import com.javaacademy.basket.repository.BasketItemRepository;
import com.javaacademy.basket.service.BasketItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemRepository repository;
    private final RestTemplate restTemplate;
    private final String PRODUCT_SERVICE = "http://localhost:9093/products/";

    public BasketItemServiceImpl(BasketItemRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public BasketItem findBasketItemByBasketIdAndProductId(String basketItemId, String productId) {
        BasketItem basketItem = repository.findBasketItemByBasket_BasketIdAndProductId(basketItemId, productId);
        return basketItem;
    }

    @Override
    public BasketItem save(BasketItem basketItem) {
        return repository.save(basketItem);
    }


    @Override
    public void delete(BasketItem basketItem) {
        repository.delete(basketItem);
    }

    public ProductDto getProductById(String productId) {
        return restTemplate.getForEntity(PRODUCT_SERVICE + productId, ProductDto.class).getBody();
    }



    public BasketItemDto toDto(BasketItem basketItem) {
        return BasketItemDto.builder()
                .basketItemId(basketItem.getBasketItemId())
                .basketItemAmount(basketItem.getBasketItemAmount())
                .count(basketItem.getCount())
                .product(getProductById(String.valueOf(basketItem.getProductId())))
                .build();
    }


}
