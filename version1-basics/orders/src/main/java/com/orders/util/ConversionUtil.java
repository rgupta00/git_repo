package com.orders.util;

import com.orders.dto.OrderDto;
import com.orders.repo.Order;

public class ConversionUtil {

    public static  OrderDto  orderToOrderDto(com.orders.repo.Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setSkuCode(order.getSkuCode());
        orderDto.setPrice(order.getPrice());
        orderDto.setQuantity(order.getQuantity());
        return orderDto;
    }
    public static Order orderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setSkuCode(orderDto.getSkuCode());
        order.setPrice(orderDto.getPrice());
        order.setQuantity(orderDto.getQuantity());
        return order;

    }
}
