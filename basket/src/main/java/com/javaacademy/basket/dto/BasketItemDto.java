package com.javaacademy.basket.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasketItemDto {
    private long basketItemId;
    private double basketItemAmount;
    private int count;
    private ProductDto product;
}
