package com.example.rabbitmq.publisher;

import com.example.rabbitmq.dto.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    private final AmqpTemplate amqpTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate, @Qualifier("amqpTemplate") AmqpTemplate amqpTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.amqpTemplate = amqpTemplate;
    }

    public void sendJsonMessage(User user){
        amqpTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }
}
