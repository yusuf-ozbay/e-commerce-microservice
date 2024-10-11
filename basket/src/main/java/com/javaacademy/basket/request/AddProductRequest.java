package com.javaacademy.basket.request;

import com.javaacademy.basket.dto.BasketDto;
import com.javaacademy.basket.dto.BasketItemDto;
import com.javaacademy.basket.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class AddProductRequest {
    private String userId;
    private String productId;
    private int count;

    public BasketDto toDto() {
        List<BasketItemDto> dtoList = new ArrayList<>();
        BasketItemDto dto = BasketItemDto.builder().product(new ProductDto(productId)).build();
        dto.setCount(count);
        dtoList.add(dto);
        return BasketDto.builder()
                .basketItemList(dtoList)
                .userId(userId)
                .build();
    }
}
