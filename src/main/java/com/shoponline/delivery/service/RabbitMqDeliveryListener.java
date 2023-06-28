package com.shoponline.delivery.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqDeliveryListener {

    private PackageService packageService;
    private ObjectMapper mapper;
    private RabbitMqTelegramPublisher rabbitMqTelegramPublisher;

    public RabbitMqDeliveryListener(PackageService packageService, ObjectMapper mapper,
                                    RabbitMqTelegramPublisher rabbitMqTelegramPublisher) {
        this.packageService = packageService;
        this.mapper = mapper.enable(JsonParser.Feature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS);
        this.rabbitMqTelegramPublisher = rabbitMqTelegramPublisher;
    }

    @RabbitListener(queues = "deliveryQueue")
    public void processDeliveryUpdate(String message) {
        log.info("Message received from delivery queue: {}", message);
        try {
            String phoneNumber = mapper.readValue(message, String.class);
            var packageDto = packageService.findByPhoneNumber(phoneNumber);
            rabbitMqTelegramPublisher.publishTelegramUpdate(packageDto.getStatus());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
