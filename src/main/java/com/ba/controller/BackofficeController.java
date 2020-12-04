package com.ba.controller;

import com.ba.dto.ProductDTO;
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
   private BackofficeService productService;

    @GetMapping("/product/list")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/product/update/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody ProductDTO product) {
        productService.updateProduct(id,product);
    }
        @GetMapping("/product/id/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/product/category/add/{id}")
    public void addProductId(@RequestBody ProductDTO product,@PathVariable Long id){
        productService.addProductId(product,id);
    }




}
