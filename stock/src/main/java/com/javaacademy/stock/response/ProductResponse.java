package com.javaacademy.stock.response;

import com.javaacademy.stock.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductResponse  {
    private Long productId;
    private String name;
    private int stock;
    private double price;
    private long categoryId;
}
