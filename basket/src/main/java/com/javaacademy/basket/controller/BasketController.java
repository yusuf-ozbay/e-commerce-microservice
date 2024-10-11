package com.javaacademy.basket.controller;

import com.javaacademy.basket.dto.BasketDto;
import com.javaacademy.basket.dto.UserDto;
import com.javaacademy.basket.request.AddProductRequest;
import com.javaacademy.basket.response.BasketResponse;
import com.javaacademy.basket.service.BasketService;
import com.javaacademy.basket.service.impl.RabbitMqJsonProducer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/baskets")
public class BasketController {

    private  BasketService basketService;
    @Autowired
    RabbitMqJsonProducer rabbitMqJsonProducer;


    @PostMapping("create")
    public BasketResponse addProductToBasket(@RequestBody AddProductRequest productRequest) {
        return toResponse(basketService.saveProductToBasket(productRequest.toDto()));
    }

    @DeleteMapping("/{userId}/{basketItemId}")
    public String removerBasketItem(@PathVariable String userId,
                                    @PathVariable String basketItemId) {
        basketService.deleteProduct(userId, basketItemId);

        return "Ürün başarıyla sepetten kaldırıldı";
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<String> register(@PathVariable String id) {
        BasketDto basketDto=basketService.findById(id);
        basketDto.setStatus(2);
        UserDto userDto=basketService.findByUserId(basketDto);
        rabbitMqJsonProducer.sendMailAddressToQueue(userDto.getEmail());
        return ResponseEntity.ok("sepetiniz satışa hazır,ödeme işlemine geçiliyor.");
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
