package com.javaacademy.basket.response;

import com.javaacademy.basket.dto.BasketItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BasketResponse  {
    private  long basketId;
    private  double totalAmount;
    private  int status;
    private  long userId;
    private  List<BasketItemDto> basketItemList;

}
