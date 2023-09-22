package com.example.core.orders.application;

import com.example.core.orders.domain.entity.Order;
import com.example.core.orders.interfaces.rest.dto.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class CreateNewOrderService {
    public Order createNewOrder(OrderRequest request) {

        return null;
    }
}
