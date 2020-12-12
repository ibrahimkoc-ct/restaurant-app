package com.ba.controller;

import com.ba.dto.ProductDTO;
import com.ba.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
