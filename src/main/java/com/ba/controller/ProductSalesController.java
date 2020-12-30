package com.ba.controller;

import com.ba.dto.ProductSalesDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.ProductSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productsales")
public class ProductSalesController {

    @Autowired
    private ProductSalesService productsales;

    @PostMapping("/add")
    public String addProductSales(@Valid @RequestBody List<ProductSalesDTO> productSalesdto) {
        productsales.addProductSales(productSalesdto);
        return "Product Sales Eklendi";

    }

    @GetMapping("/list")
    public List<ProductSalesDTO> getAllProduct() {
        return productsales.getAllProductSales();
    }

}
