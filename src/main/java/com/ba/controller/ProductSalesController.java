package com.ba.controller;



import com.ba.entity.ProductSales;
import com.ba.service.ProductSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productsales")
public class ProductSalesController {

    @Autowired
    private ProductSalesService productsales;

    @PostMapping("/add")
    public void addProductSales(@RequestBody List<ProductSales> productSales) {

        productsales.addProductSales(productSales);

    }
    @GetMapping("/list")
    public List<ProductSales> getAllProduct() {
        return productsales.getAllProductSales();
    }

}
