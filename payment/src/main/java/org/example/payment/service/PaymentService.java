package org.example.payment.service;

import org.example.payment.dto.BasketDto;
import org.example.payment.dto.PaymentDto;
import org.example.payment.dto.UserDto;

public interface PaymentService {

     int paymentStatus(BasketDto basketDto);
     UserDto findUserById(BasketDto basketDto);
     BasketDto findBasketById(PaymentDto paymentDto);
     String processPayment(PaymentDto paymentDto);
     String register(PaymentDto paymentDto);


}
