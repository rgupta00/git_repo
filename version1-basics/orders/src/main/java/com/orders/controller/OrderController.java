package com.orders.controller;

import com.orders.dto.OrderDto;
import com.orders.dto.OrderPlacedEvent;
import com.orders.proxy.InventoryClient;
import com.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate; // <key, value>
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderDto orderDto) {
        boolean isInStock = inventoryClient
                .isInStock(orderDto.getSkuCode(), orderDto.getQuantity());
        if (!isInStock) {
            throw new RuntimeException("Product is not in stock");
        }
        orderService.placeOrder(orderDto);
        //send the message to the kafka topic once the order is placed
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
        orderPlacedEvent.setOrderNumber(orderDto.getOrderNumber());
        orderPlacedEvent.setCustomerEmail(orderDto.getCustomerEmail());

        log.info("sending Order to Placed Event: {}", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
        log.info("End Order to Placed Event: {}", orderPlacedEvent);
        return "Order Placed Successfully";
    }
}