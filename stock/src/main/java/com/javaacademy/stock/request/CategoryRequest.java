package com.javaacademy.stock.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.stock.dto.CategoryDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    private String name;


    public CategoryDto toDto(){
        return CategoryDto.builder()
                .name(this.name)
                .build();
    }
}
