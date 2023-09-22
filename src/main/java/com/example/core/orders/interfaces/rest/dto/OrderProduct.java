package com.example.core.orders.interfaces.rest.dto;

import lombok.Getter;

@Getter
public class OrderProduct {
    private long productId;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
