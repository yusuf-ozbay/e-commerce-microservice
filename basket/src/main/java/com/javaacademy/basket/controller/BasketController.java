package com.javaacademy.basket.controller;

import com.javaacademy.basket.dto.BasketDto;
import com.javaacademy.basket.request.AddProductRequest;
import com.javaacademy.basket.response.BasketResponse;
import com.javaacademy.basket.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/baskets")
public class BasketController {

    private  BasketService basketService;


    @PostMapping("create")
    public BasketResponse addProductToBasket(@RequestBody AddProductRequest productRequest) {
        return toResponse(basketService.addProductToBasket(productRequest.toDto()));
    }

    @DeleteMapping("/{userId}/{basketItemId}")
    public String removerBasketItem(@PathVariable String userId,
                                    @PathVariable String basketItemId) {
        basketService.removeProductFromBasket(userId, basketItemId);

        return "Ürün başarıyla sepetten kaldırıldı";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BasketResponse>  getBasketByUserId(@PathVariable String userId){
        return ResponseEntity.ok(toResponse(basketService.getBasketByUserId(userId)));
    }

    public BasketResponse toResponse(BasketDto basketDto) {
        return BasketResponse.builder()
                .basketId(basketDto.getBasketId())
                .totalAmount(basketDto.getTotalAmount())
                .status(basketDto.getStatus())
                .userId(basketDto.getUserId())
                .basketItemList(basketDto.getBasketItemList())
                .build();
    }



}
