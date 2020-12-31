package com.ba.controller;

import com.ba.entity.OrderItem;
import com.ba.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order-item")
public class OrderItemController {
    @Autowired
    private OrderItemService service;

    @GetMapping("/{id}")
    public List<OrderItem> getOrderItemByOrderId(@PathVariable Long id){
        return service.getOrderItemByOrderId(id);
    }
}
