package com.order_service.controller;

import com.order_service.model.Order;
import com.order_service.services.OrderSagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderSagaService orderSagaService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderSagaService.placeOrder(order);
    }
}