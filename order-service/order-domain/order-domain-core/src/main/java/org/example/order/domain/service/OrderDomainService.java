package org.example.order.domain.service;

import org.example.order.domain.entity.Order;
import org.example.order.domain.event.OrderCreatedEvent;
import java.time.Instant;

public class OrderDomainService {
    public OrderCreatedEvent validateAndInitiateOrder(Order order) {
        order.validateOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, Instant.now());
    }
}
