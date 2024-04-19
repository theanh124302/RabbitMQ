package com.example.rabbitmq.controller;

import com.example.rabbitmq.dto.User;
import com.example.rabbitmq.publisher.RabbitMQJsonProducer;
import com.example.rabbitmq.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {

    @Autowired
    private RabbitMQJsonProducer jsonProducer;

    public JsonMessageController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    // http://localhost:8080/api/v1/publish/json
    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitJsonMQ ...");
    }

}