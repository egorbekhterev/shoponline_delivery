package com.shoponline.delivery.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMqTelegramPublisher {

    private RabbitTemplate rabbitTemplate;

    public void publishTelegramUpdate(String message) {
        rabbitTemplate.convertAndSend("telegramQueue", message);
    }
}
