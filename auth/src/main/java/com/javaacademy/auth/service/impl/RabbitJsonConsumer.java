package com.javaacademy.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RabbitJsonConsumer {

    @Autowired
    private JavaMailSender javaMailSender;
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitJsonConsumer.class);


    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void receiveMessage(String email) {
        sendEmail(email);
    }

    private void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("SEPET DURUMU: ");
        message.setText("sepetiniz hazır, ödeme işlemini gerçekleştirin :) ");
        try {
            javaMailSender.send(message);
            LOGGER.info(String.format("E posta başarıyla gönderildi",email));
        } catch (MailException e) {
            System.err.println("E-posta gönderim hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
