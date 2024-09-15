package com.javaacademy.stock.request;

import com.javaacademy.stock.dto.CategoryDto;
import com.javaacademy.stock.dto.ProductDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private int stock;
    private double price;
    private long categoryId;

    public ProductDto toDto(){
        return ProductDto.builder()
                .name(this.name)
                .stock(this.stock)
                .price(this.price)
                .categoryId(this.categoryId)
                .build();
    }
}
