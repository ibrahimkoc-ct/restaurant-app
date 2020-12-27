package com.ba.controller;

import com.ba.dto.ProductDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        productService.deleteProduct(id);
        return "ürün silindi";
    }
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
        if (product==null || product.getId()==null){
            throw new BussinessRuleException("Product cannot be empty!");
        }
        productService.updateProduct(id,product);
        return product;
    }
    @GetMapping("/id/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return productService.getProductById(id);
    }
    @PostMapping()
    public String addProductId(@RequestBody ProductDTO product){
        if (product == null || product.getId() !=null) {
            throw new BussinessRuleException("Product cannot be empty!");
        }
        productService.addProductId(product);
        return "product";
    }
    @GetMapping("/page")
    public Page<ProductDTO> searchProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return  productService.getPageProduct(page,size);
    }
    @GetMapping("/category/{categoryName}")
    public List<ProductDTO> findCategory(@PathVariable String categoryName){
        if (categoryName == null) {
            throw new BussinessRuleException("Category Name cannot be empty!");
        }
        return  productService.listSelectedCategory(categoryName);
    }
    @GetMapping("/search/{id}/")
    public Slice<ProductDTO> loadMoreProduct(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return productService.loadMoreProduct(id,page,size);
    }

}
