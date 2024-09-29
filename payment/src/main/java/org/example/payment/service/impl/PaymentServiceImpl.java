package org.example.payment.service.impl;

import com.javaacademy.basket.exception.ResourceNotFoundException;
import org.example.payment.dto.BasketDto;
import org.example.payment.dto.PaymentDto;
import org.example.payment.dto.UserDto;
import org.example.payment.entity.Payment;
import org.example.payment.repository.PaymentRepository;
import org.example.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RabbitMqJsonProducer rabbitMqJsonProducer;

    @Autowired
    PaymentRepository repository;

    RestTemplate restTemplate=new RestTemplate();
    private final String getUserByIdViaGatewayyUrl = "http://localhost:9092/auth/user/getById/";
    private final String getBasketByIdViaGatewayyUrl="http://localhost:9094/basket/basket/getById/";


    //Bu metot, BasketDto nesnesinin durumunu kontrol ederek eğer durum 1 ise onu 3 olarak günceller
    // ve 3 değerini döndürür; durum 0 ise 0 döner, aksi takdirde 0 döndürür.
    @Override
    public int paymentStatus(BasketDto basketDto) {
        int value=0;
        if (basketDto.getStatus()==1){
            basketDto.setStatus(3);
            value=3;
        } else if (basketDto.getStatus()==0) {
            return value;
        }
        return value;
    }

    @Override
    public UserDto findUserById(BasketDto basketDto) {
        UserDto userDto=restTemplate.getForObject(getUserByIdViaGatewayyUrl+basketDto.getUserId(),UserDto.class);
        return userDto;
    }

    @Override
    public BasketDto findBasketById(PaymentDto paymentDto) {
        return restTemplate.getForObject(getBasketByIdViaGatewayyUrl+paymentDto.getBasketId(),BasketDto.class);
    }


    @Override
     public String processPayment(PaymentDto paymentDto) {
        if (paymentDto.getBasketId() == null) {
            throw new IllegalArgumentException("Basket Id is required.");
        }
        if (paymentDto.getCardNumber() == null || paymentDto.getCardNumber().isEmpty()) {
            throw new IllegalArgumentException("Card number is invalid.");
        }
        if (paymentDto.getMonth() == null) {
            throw new IllegalArgumentException("Month is invalid.");
        }
        if (paymentDto.getYear() == null) {
            throw new IllegalArgumentException("Year is invalid.");
        }
        BasketDto basketDto = findBasketById(paymentDto);
        if (basketDto == null) {
            throw new IllegalArgumentException("Basket not found.");
        }
        if (basketDto.getStatus() != 1) {
            throw new IllegalArgumentException("Sepet satışa uygun değil");

        }
        return "Payment for basket " + paymentDto.getBasketId() + " processed successfully.";
    }

    @Override
    public String register(PaymentDto paymentDto) {
        String result = processPayment(paymentDto);
        System.out.println(result);
        BasketDto basketDto = findBasketById(paymentDto);
        paymentStatus(basketDto);
        UserDto userDto = findUserById(basketDto);
        rabbitMqJsonProducer.sendMailAddressToQueue(userDto.getEmail());
        return result;
    }

    public Double paymentAmount(PaymentDto paymentDto) {
        BasketDto basketDto = findBasketById(paymentDto);
        paymentDto.setAmount(basketDto.getTotalPrice());
        return paymentDto.getAmount();
    }

    public PaymentDto findPaymentById(Long id) {
        Payment payment=repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Payment not found with id" + id));
        return toDto(payment);
    }

    public String checkout(PaymentDto paymentDto) {

        Payment payment = toEntity(paymentDto);
        payment = repository.save(payment);
        paymentDto = toDto(payment);
        String result = processPayment(paymentDto);
        System.out.println(result);
        BasketDto basketDto = findBasketById(paymentDto);
        paymentStatus(basketDto);
        UserDto userDto = findUserById(basketDto);
        rabbitMqJsonProducer.sendMailAddressToQueue(userDto.getEmail());
        return result;

    }

    public Payment toEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setBasketId(dto.getBasketId());
        payment.setAmount(paymentAmount(dto));
        payment.setYear(dto.getYear());
        payment.setMonth(dto.getMonth());
        payment.setCardNumber(dto.getCardNumber());
        return payment;
    }

    public PaymentDto toDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setBasketId(payment.getBasketId());
        dto.setAmount(payment.getAmount());
        dto.setYear(payment.getYear());
        dto.setMonth(payment.getMonth());
        dto.setCardNumber(payment.getCardNumber());
        return dto;
    }


}