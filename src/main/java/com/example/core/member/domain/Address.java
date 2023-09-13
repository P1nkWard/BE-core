package com.example.core.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
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
}
