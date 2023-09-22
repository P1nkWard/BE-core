package com.example.core.orders.interfaces.rest;

import com.example.core.document.config.RestDocsTestSupport;
import com.example.core.global.model.Money;
import com.example.core.orders.application.CreateNewOrderService;
import com.example.core.orders.domain.entity.Order;
import com.example.core.orders.domain.vo.OrderNo;
import com.example.core.orders.domain.vo.OrderState;
import com.example.core.orders.domain.vo.Orderrer;
import com.example.core.orders.domain.entity.OrderLine;
import com.example.core.orders.domain.vo.Product;
import com.example.core.orders.interfaces.rest.dto.OrderProduct;
import com.example.core.orders.interfaces.rest.dto.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OrderProcessingAPI.class})
class OrderProcessingAPITest extends RestDocsTestSupport {
    @MockBean
    private CreateNewOrderService createNewOrderService;

    @Autowired
    private ObjectMapper objectMapper;
    private final static String ORDERER_ID = "TEST_CUSTOMER";
    private final static String ORDER_ID = "order:2000123453";

    @Test
    @DisplayName(value = "주문 생성")
    public void 주문_생성() throws Exception {
        //given
        List<OrderLine> list = new ArrayList<>();
        List<OrderProduct> orderProductList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new OrderLine(new Product(i), new Money((i + 1) * 1000), i + 1));
            orderProductList.add(new OrderProduct(i, i + 1));
        }

        OrderRequest orderRequest = OrderRequest.builder()
                .orderrer(new Orderrer(ORDERER_ID))
                .orderProducts(orderProductList)
                .build();

        String request = objectMapper.writeValueAsString(orderRequest);

        // when
        Order order = new Order(new OrderNo(ORDER_ID), new Orderrer(ORDERER_ID), list, OrderState.PREPARING);
        when(createNewOrderService.createNewOrder(any())).thenReturn(order);

        // then
        mvc.perform(post("/api/orders").content(request))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}