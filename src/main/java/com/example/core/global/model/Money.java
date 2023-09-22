package com.example.core.global.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Embeddable
public class Money {
    private int value;

    public Money(int value) {
        this.value = value;
    }

    public Money() {
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
