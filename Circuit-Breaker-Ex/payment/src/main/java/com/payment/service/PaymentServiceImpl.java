package com.payment.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String processPayment(int orderId) {

        // simulate failure
        if (orderId > 5) {
            throw new RuntimeException("Payment Service is Down!");
        }

        return "Payment Successful for Order " + orderId;
    }
}