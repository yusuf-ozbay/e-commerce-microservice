package org.example.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private String id;
    private String basketId;
    private String cardNumber;
    private String month;
    private String year;
    private Double amount;

}
