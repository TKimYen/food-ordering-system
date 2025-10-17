package com.example.order.domain.valueobject;

import java.util.UUID;

public class TrackingId {
    private final UUID value;

    public TrackingId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
