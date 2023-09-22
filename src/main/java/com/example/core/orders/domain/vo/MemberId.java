package com.example.core.orders.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class MemberId implements Serializable {
    @Column(name = "id")
    private String id;


    public MemberId() {

    }

    public MemberId(String id) {
        this.id = id;

    }
}
