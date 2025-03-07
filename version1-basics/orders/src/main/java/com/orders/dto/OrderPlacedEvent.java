package com.orders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String customerEmail;
}