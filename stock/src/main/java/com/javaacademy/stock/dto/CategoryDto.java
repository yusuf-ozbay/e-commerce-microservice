package com.javaacademy.stock.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private long categoryId;
    private String name;
}
