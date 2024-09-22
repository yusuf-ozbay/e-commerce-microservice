package org.example.payment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    public String exchange;
    @Value("${rabbitmq.routing.json.key}")
    public String routingJsonKey;
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;


    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMqJsonProducer.class);

    private RabbitTemplate rabbitTemplate;
    public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    public void sendMailAddressToQueue(String mail){
        rabbitTemplate.convertAndSend(jsonQueue,mail);
    }


}
