package com.ba.service;

import com.ba.entity.OrderItem;
import com.ba.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> getOrderItemByOrderId(Long id){
        return repository.findOrderItemByOrderId(id);
    }
}
