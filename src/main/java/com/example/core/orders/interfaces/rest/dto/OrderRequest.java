package com.example.core.orders.interfaces.rest.dto;


import com.example.core.orders.domain.vo.Orderrer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequest {
    private Orderrer orderrer;
    private List<OrderProduct> orderProducts;

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orderer=" + orderrer +
                ", orderProducts=" + orderProducts +
                '}';
    }
}
