package com.example.core.member.domain;

import jakarta.validation.constraints.NotBlank;

public class Phone {
    @NotBlank
    private String first;
    @NotBlank
    private String middle;
    @NotBlank
    private String last;

    protected Phone(){}
    public Phone(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getMiddle() {
        return middle;
    }

    public String getLast() {
        return last;
    }
}
