package com.inventory_service.controller;

import com.inventory_service.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @PostMapping("/reserve")
    public String reserveInventory(@RequestBody Order order){

        System.out.println("Reserving product " + order.getProductId());

        boolean stockAvailable = true;

        if(stockAvailable){
            return "SUCCESS";
        }

        return "FAILED";
    }
}