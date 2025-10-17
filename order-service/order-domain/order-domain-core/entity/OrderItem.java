package com.example.order.domain.entity;

import com.example.order.domain.valueobject.Money;
import com.example.order.domain.valueobject.ProductId;

public class OrderItem {
    private final ProductId productId;
    private final Money price;
    private final int quantity;
    private final Money subTotal;

    public OrderItem(ProductId productId, Money price, int quantity, Money subTotal) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public void validateItemPrice() {
        if (price.isNegative() || price.isZero()) {
            throw new IllegalArgumentException("Item price must be positive!");
        }
        if (!price.multiply(quantity).equals(subTotal)) {
            throw new IllegalArgumentException("Subtotal does not match price * quantity!");
        }
    }

    // Getters
    public ProductId getProductId() { return productId; }
    public Money getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Money getSubTotal() { return subTotal; }
}
