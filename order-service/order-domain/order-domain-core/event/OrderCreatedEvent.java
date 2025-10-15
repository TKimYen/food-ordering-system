package com.example.order.domain.event;

import com.example.order.domain.entity.Order;
import java.time.Instant;

public class OrderCreatedEvent {
    private final Order order;
    private final Instant createdAt;

    public OrderCreatedEvent(Order order, Instant createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() { return order; }
    public Instant getCreatedAt() { return createdAt; }
}
