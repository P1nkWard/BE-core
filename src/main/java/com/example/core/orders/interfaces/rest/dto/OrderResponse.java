package com.example.core.orders.interfaces.rest.dto;


import com.example.core.orders.domain.entity.Order;
import com.example.core.orders.domain.vo.OrderNo;

public class OrderResponse extends RestResponse<OrderNo> {

    public OrderResponse(int status, OrderNo data, String message) {
        super(status, data, message);
    }

    public static OrderResponse success(Order data) {
        OrderNo orderNo = data.getNumber();
        return new OrderResponse(200, orderNo, String.format("%s 정삭적으로 주문이 완료되었습니다.", orderNo.getNumber()));
    }

    public static OrderResponse fail() {
        return new OrderResponse(400, null, "주문이 실패하였습니다.");
    }
}
