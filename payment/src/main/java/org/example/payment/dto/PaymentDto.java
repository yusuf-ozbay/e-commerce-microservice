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
public class PaymentDto {
    private Long id;
    private Long basketId;
    private String cardNumber;
    private String month;
    private String year;
    private Double amount;
}
