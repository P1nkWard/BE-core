package com.example.core.orders.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Orderrer {
    @Embedded
    private MemberId memberId;


    public Orderrer() {
    }

    public Orderrer(String memberId) {
        this.memberId = new MemberId(memberId);
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
