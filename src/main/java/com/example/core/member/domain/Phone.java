package com.example.core.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Phone {
    @NotBlank
    private String first;
    @NotBlank
    private String middle;
    @NotBlank
    private String last;

    public Phone(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(first, phone.first) && Objects.equals(middle, phone.middle) && Objects.equals(last, phone.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, middle, last);
    }
}