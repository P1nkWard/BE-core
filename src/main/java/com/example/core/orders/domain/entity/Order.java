package com.example.core.orders.domain.entity;

import com.example.core.global.model.Money;
import com.example.core.orders.domain.vo.OrderNo;
import com.example.core.orders.domain.vo.OrderState;
import com.example.core.orders.domain.vo.Orderrer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @EmbeddedId
    private OrderNo number;

    @Embedded
    private Orderrer orderrer;

    @ElementCollection
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    @Column(name = "total_amounts")
    private Money totalAmounts;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState state;


    private LocalDateTime orderDate;

    public void setNumber(OrderNo number) {
        if (number == null) throw new IllegalArgumentException("no number");
        this.number = number;
    }

    public void setOrderrer(Orderrer orderrer) {
        if (orderrer == null) throw new IllegalArgumentException("no orderrer");
        this.orderrer = orderrer;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(this.orderLines.stream().mapToInt(x -> x.getAmounts().getValue()).sum());
    }

    public void verifyAtLeastOneOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    public Order(OrderNo number, Orderrer orderrer, List<OrderLine> orderLines,
                 OrderState state) {
        setNumber(number);
        setOrderrer(orderrer);
        setOrderLines(orderLines);
        this.state = state;
        this.orderDate = LocalDateTime.now();
    }
}
