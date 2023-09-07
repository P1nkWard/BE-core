package com.example.core.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Embeddable
public class Address {
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String detail;

    protected Address() {
    }

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

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getDetail() {
        return detail;
    }
}
