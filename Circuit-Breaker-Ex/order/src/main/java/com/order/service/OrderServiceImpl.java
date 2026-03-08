package com.order.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "payment", fallbackMethod = "paymentFallback")
    public String createOrder(int id) {

        String paymentResponse = restTemplate.getForObject(
                "http://localhost:8081/payment/" + id,
                String.class
        );

        return "Order Created Successfully. ";
    }

    // fallback method
    public String paymentFallback(int id, Throwable ex) {

        return "Order Created, but Payment Service is currently unavailable.";
    }
}