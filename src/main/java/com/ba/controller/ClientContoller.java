package com.ba.controller;

import com.ba.dto.ProductDTO;
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
    private ClientService clientService;

    @GetMapping("/product/id/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return clientService.getProductById(id);
    }
    @GetMapping("/product/list")
    public List<ProductDTO> getAllProduct() {
        return clientService.getAllProduct();
    }
    @GetMapping("/product/{categoryName}")
            public List<ProductDTO> findCategory(@PathVariable String categoryName){
            return  clientService.listSelectedCategory(categoryName);
    }


}
