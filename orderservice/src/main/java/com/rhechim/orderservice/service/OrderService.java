package com.rhechim.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.rhechim.inventoryservice.entity;
@Service
@Slf4j
public class OrderService {

    @KafkaListener(topics="booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Order event received: {}", bookingEvent);


    }
}
