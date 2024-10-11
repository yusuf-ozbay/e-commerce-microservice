package com.javaacademy.basket.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto  {

    private String productId;
    private String name;
    private int stock;
    private double price;

    public ProductDto(String productId){
        this.productId = productId;
    }

}
