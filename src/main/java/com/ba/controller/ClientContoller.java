package com.ba.controller;

import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import com.ba.service.BackofficeService;
import com.ba.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/client")
public class ClientContoller {
    @Autowired
    ProductRepository repository;

    @Autowired
    private ClientService clientService;

    @GetMapping("/product/id/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return clientService.getProductById(id);
    }
    @GetMapping("/product/list")
    public List<Product> getAllProduct() {
        return clientService.getAllProduct();
    }

}
