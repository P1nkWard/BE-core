package com.example.core.member.domain;

import jakarta.validation.constraints.NotBlank;

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
