package com.example.order.domain.entity;

import com.example.order.domain.exception.OrderDomainException;
import com.example.order.domain.valueobject.*;
import java.util.*;

public class Order {
    private final OrderId id;
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus status;
    private final List<String> failureMessages = new ArrayList<>();

    public Order(OrderId id, CustomerId customerId, RestaurantId restaurantId,
                 StreetAddress deliveryAddress, Money price, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
        this.price = price;
        this.items = items;
    }

    public void initializeOrder() {
        trackingId = new TrackingId(UUID.randomUUID());
        status = OrderStatus.PENDING;
    }

    public void validateOrder() {
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateTotalPrice() {
        if (price == null || price.isZero() || price.isNegative()) {
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    private void validateItemsPrice() {
        Money total = items.stream()
                .map(OrderItem::getSubTotal)
                .reduce(Money.ZERO, Money::add);
        if (!total.equals(price)) {
            throw new OrderDomainException("Total price does not match sum of order items!");
        }
    }

    public void pay() {
        if (status != OrderStatus.PENDING) {
            throw new OrderDomainException("Order not in correct state for pay operation!");
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
    if (orderStatus == OrderStatus.PAID || orderStatus == OrderStatus.PENDING) {
        orderStatus = OrderStatus.CANCELLED;
    } else {
        throw new OrderDomainException("Order cannot be cancelled in state: " + orderStatus);
    }
}


    // Getters
    public OrderId getId() { return id; }
    public TrackingId getTrackingId() { return trackingId; }
    public OrderStatus getStatus() { return status; }
}
