package com.order_service.services;

import com.order_service.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderSagaService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Order placeOrder(Order order){

        order.setStatus("CREATED");

        try{

            String paymentResponse =
                    restTemplate.postForObject(
                            "http://localhost:8081/payment/process",
                            order,
                            String.class
                    );

            if(!"SUCCESS".equals(paymentResponse)){
                throw new RuntimeException("Payment Failed");
            }

            String inventoryResponse =
                    restTemplate.postForObject(
                            "http://localhost:8082/inventory/reserve",
                            order,
                            String.class
                    );

            if(!"SUCCESS".equals(inventoryResponse)){
                throw new RuntimeException("Inventory Failed");
            }

            order.setStatus("CONFIRMED");

        }catch(Exception e){

            restTemplate.postForObject(
                    "http://localhost:8081/payment/refund",
                    order,
                    String.class
            );

            order.setStatus("CANCELLED");
        }

        return order;
    }
}