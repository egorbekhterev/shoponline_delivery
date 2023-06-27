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

    public RabbitMqDeliveryListener(PackageService packageService, ObjectMapper mapper) {
        this.packageService = packageService;
        this.mapper = mapper.enable(JsonParser.Feature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS);
    }

    @RabbitListener(queues = "deliveryQueue")
    public void processInventoryUpdate(String message) {
        log.info("Message received from queue: {}", message);
        try {
            String phoneNumber = mapper.readValue(message, String.class);
            packageService.findByPhoneNumber(phoneNumber);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
