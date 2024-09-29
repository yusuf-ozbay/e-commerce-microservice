package org.example.payment.controller;

import org.example.payment.dto.PaymentDto;
import org.example.payment.request.PaymentRequest;
import org.example.payment.response.PaymentResponse;
import org.example.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody PaymentRequest request) {   //requestlin bilgilerin girip istek atacaz
        try {

            String result = paymentService.checkout(toDto(request));
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }

    @GetMapping("/getById")
    public PaymentResponse getById(@PathVariable Long id){
        return toResponse(paymentService.findPaymentById(id));
    }



    private PaymentDto toDto(PaymentRequest request){
        PaymentDto dto=new PaymentDto();
        dto.setBasketId(request.getBasketId());
        dto.setCardNumber(request.getCardNumber());
        dto.setMonth(request.getMonth());
        dto.setYear(request.getYear());
        return dto;
    }
    private PaymentResponse toResponse(PaymentDto dto){
        PaymentResponse response=new PaymentResponse();
        response.setId(dto.getId());
        response.setYear(dto.getYear());
        response.setMonth(dto.getMonth());
        response.setCardNumber(dto.getCardNumber());
        response.setBasketId(dto.getBasketId());
        response.setAmount(dto.getAmount());
        return response;
    }

}
