package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import com.ba.service.BackofficeService;
import liquibase.pro.packaged.L;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

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
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "ürün silindi";
    }

    @PutMapping("/product/update/{id}")
    public ProductDTO updateProduct(@PathVariable long id, @RequestBody ProductDTO product) {
        productService.updateProduct(id,product);
        return product;
    }
        @GetMapping("/product/id/{id}")
    public ProductDTO getProductById(@PathVariable Long id){

        return productService.getProductById(id);
    }

    @PostMapping("/product/category/add/{id}")
    public String addProductId(@RequestBody ProductDTO product, @PathVariable Long id){
       productService.addProductId(product,id);

        return "product";
    }




}
