package com.javaacademy.basket.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private long basketId;
    private double totalAmount;
    private int status;
    private long userId;
    private  List<BasketItemDto> basketItemList;

}
