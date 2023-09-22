package com.example.core.orders.interfaces.rest;

import com.example.core.orders.application.CreateNewOrderService;
import com.example.core.orders.domain.entity.Order;
import com.example.core.orders.interfaces.rest.dto.OrderRequest;
import com.example.core.orders.interfaces.rest.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/orders")
@RequiredArgsConstructor
public class OrderProcessingAPI {
    private final CreateNewOrderService createNewOrderService;

    @PostMapping
    public ResponseEntity<?> createNewOrder(OrderRequest request) {
        Order newOrder = createNewOrderService.createNewOrder(request);

        OrderResponse success = OrderResponse.success(newOrder);

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

}
