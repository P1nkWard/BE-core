package com.example.core.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
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
}
