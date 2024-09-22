package org.example.payment.dto;

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
public class BasketProductDto {

    private Long id;
    private int count;
    private Double totalPrice;
    private Long productId;
    private String productName;


    public  BasketProductDto(Long productId,int count){
        this.productId=productId;
        this.count=count;
    }


}
