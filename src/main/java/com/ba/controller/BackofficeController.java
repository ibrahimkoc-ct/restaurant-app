package com.ba.controller;

import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import com.ba.service.BackofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {
    @Autowired
    ProductRepository repository;

    @Autowired
   private BackofficeService productService;

    @PostMapping("/product/add")
    public List<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @GetMapping("/product/list")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @DeleteMapping("/product/delete/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
       return productService.deleteProduct(id);
    }

    @PutMapping("/product/update/{id}")
    public List<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        return productService.updateProduct(id,product);
    }
    @GetMapping("/product/id/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }




}
