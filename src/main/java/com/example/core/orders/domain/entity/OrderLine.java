package com.example.core.orders.domain.entity;

import com.example.core.global.model.Money;
import com.example.core.orders.domain.vo.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class OrderLine {
    @Embedded
    private Product product;
    private Money price;

    private int quantity;

    private Money amounts;

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = this.calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }

}
