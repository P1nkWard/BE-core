package com.example.core.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String detail;

    public Address(String city, String street, String detail) {
        this.city = city;
        this.street = street;
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(detail, address.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, detail);
    }
}