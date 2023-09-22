package com.example.core.orders.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
public class Product {
    @Column(name = "product_id")
    private long id;

    public Product() {

    }

    public Product(long id) {
        this.id = id;
    }
}
