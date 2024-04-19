package com.example.rabbitmq.publisher;

import com.example.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);


    private RabbitTemplate rabbitTemplate;


    private final AmqpTemplate amqpTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate, @Qualifier("amqpTemplate") AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Message sent -> %s", user));
        amqpTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, message);
    }

}
