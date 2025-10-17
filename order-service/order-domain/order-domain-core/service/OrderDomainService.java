package com.example.order.domain.service;

import com.example.order.domain.entity.Order;
import com.example.order.domain.event.OrderCreatedEvent;
import java.time.Instant;

public class OrderDomainService {
    public OrderCreatedEvent validateAndInitiateOrder(Order order) {
        order.validateOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, Instant.now());
    }
}
