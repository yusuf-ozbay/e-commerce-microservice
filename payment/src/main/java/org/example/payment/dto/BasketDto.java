package org.example.payment.dto;

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
public class BasketDto {

    private String id;
    private int status;
    private Double totalPrice;
    private String userId;
    private List<BasketProductDto> basketProducts;
    private int count;

}
