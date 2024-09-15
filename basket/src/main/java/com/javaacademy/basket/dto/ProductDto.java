package com.javaacademy.basket.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto  {

    private long productId;
    private String name;
    private int stock;
    private double price;

    public ProductDto(long productId){
        this.productId = productId;
    }

}
