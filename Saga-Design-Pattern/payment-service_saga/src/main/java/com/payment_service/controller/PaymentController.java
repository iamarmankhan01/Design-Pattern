package com.payment_service.controller;


import com.payment_service.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/process")
    public String processPayment(@RequestBody Order order){

        System.out.println("Processing payment for order " + order.getOrderId());

        boolean paymentSuccess = true;

        if(paymentSuccess){
            return "SUCCESS";
        }

        return "FAILED";
    }

    @PostMapping("/refund")
    public String refundPayment(@RequestBody Order order){

        System.out.println("Refund payment for order " + order.getOrderId());

        return "REFUND_SUCCESS";
    }
}