package com.javaacademy.stock.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto{

    private Long productId;
    private String name;
    private int stock;
    private double price;
    private long categoryId;
}
