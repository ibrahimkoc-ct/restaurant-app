package com.ba.controller;



import com.ba.dto.ProductSalesDTO;
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
    public void addProductSales(@RequestBody List<ProductSalesDTO> productSalesdto) {

        productsales.addProductSales(productSalesdto);

    }
    @GetMapping("/list")
    public List<ProductSalesDTO> getAllProduct() {
        return productsales.getAllProductSales();
    }

}
