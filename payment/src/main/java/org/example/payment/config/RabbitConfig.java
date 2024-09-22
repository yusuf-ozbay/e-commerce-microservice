package org.example.payment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

// Bu sınıf, RabbitMQ yapılandırmalarını tanımlamak için kullanılır.
// @Configuration anotasyonu ile Spring uygulama bağlamında bir yapılandırma sınıfı olarak işaretlenir.
@Getter
@Setter
@Configuration
public class RabbitConfig {

    // (application.properties veya application.yml) kuyruk adını alır.
    // Bu değer, RabbitMQ'daki kuyruk isimlerini dinamik olarak tanımlamak için kullanılır.
    @Value("${rabbitmq.queue.json.name}")
    private String queue;

    //  JSON kuyruk adını alır.
    // Bu değer, JSON formatında mesajlar göndermek için kullanılan özel bir kuyruk ismi sağlar.
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    //  exchange adını alır.
    // Exchange, RabbitMQ'da mesajların hangi kuyruklara yönlendirileceğini belirleyen bir yapıdır.
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    //  routing key'i alır.
    // Routing key, mesajların hangi kuyruklara yönlendirileceğini belirlemek için kullanılır.
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //  JSON için routing key'i alır.
    // Bu, JSON formatındaki mesajların yönlendirilmesi için kullanılan özel bir routing key'dir.
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    // Kuşak oluşturma metodudur.
    @Bean
    public Queue queue() {
        // Belirtilen isimde bir kuyruk oluşturur.
        // Bu kuyruk, standart mesajların saklanacağı yerdir.
        return new Queue(queue);
    }

    // JSON kuşak oluşturma metodudur.
    @Bean
    public Queue jsonQueue() {
        // Belirtilen isimde bir JSON kuyruk oluşturur.
        // Bu kuyruk, JSON formatındaki mesajların saklanacağı yerdir.
        return new Queue(jsonQueue);
    }

    // Exchange oluşturma metodudur.
    @Bean
    public TopicExchange exchange() {
        // Belirtilen isimde bir TopicExchange oluşturur.
        // Exchange, mesajların yönlendirilmesi için bir yol sağlarken,
        // aynı zamanda mesajları çeşitli kuyruklara dağıtmak için kullanılır.
        return new TopicExchange(exchange);
    }

    // Standart kuyruk ile exchange arasında bağlantı kurma metodudur.
    @Bean
    public Binding binding() {
        // queue ve exchange arasında belirtilen routing key ile bir bağlantı oluşturur.
        // Bu bağlantı, mesajların doğru kuyruklara yönlendirilmesini sağlar.
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    // JSON kuyruk ile exchange arasında bağlantı kurma metodudur.
    @Bean
    public Binding jsonBinding() {
        // jsonQueue ve exchange arasında belirtilen JSON routing key ile bir bağlantı oluşturur.
        // Bu bağlantı, JSON formatındaki mesajların doğru kuyruklara yönlendirilmesini sağlar.
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    // Mesaj dönüştürücü oluşturma metodudur.
    @Bean
    public MessageConverter converter() {
        // Mesajları JSON formatında dönüştürmek için Jackson2JsonMessageConverter kullanılır.
        // Bu, RabbitMQ ile gönderilen ve alınan mesajların JSON formatında doğru şekilde işlenmesini sağlar.
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate oluşturma metodudur.
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        // RabbitTemplate nesnesi oluşturur ve mesaj dönüştürücüyü ayarlar.
        // RabbitTemplate, RabbitMQ ile mesaj gönderme ve alma işlemlerini kolaylaştırır.
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter()); // Mesaj dönüştürücüsünü ayarlar.
        return rabbitTemplate; // Hazırlanan RabbitTemplate nesnesini döndürür.
    }

}
