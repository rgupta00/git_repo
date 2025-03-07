package com.notifications.service;

import com.orders.dto.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "order-placed", groupId = "notification-service")
    public void sendNotification(OrderPlacedEvent orderPlacedEvent) {
        System.out.println(orderPlacedEvent);
        log.info("Sending notification to customer {} for order {}",
                orderPlacedEvent.getCustomerEmail(), orderPlacedEvent.getOrderNumber());
    }
}