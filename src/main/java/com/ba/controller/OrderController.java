package com.ba.controller;

import com.ba.dto.OrderandCreditDTO;
import com.ba.entity.Order;
import com.ba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping()
    public ResponseEntity<String> orderandCreditAdd(@RequestBody OrderandCreditDTO orderandCreditDTO){
            service.addOrderandCreditCard(orderandCreditDTO);
        return new ResponseEntity<>("Order and Credit Card", HttpStatus.OK);
    }
    @GetMapping()
    public List<Order> getAllOrder(){
        return service.getAllOrder();
    }

}
