package com.ba.controller;

import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.entity.Users;
import com.ba.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public List<Category> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    @GetMapping("/list")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
    @GetMapping("/id/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/update")
    public List<Category> updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
    @GetMapping("/product/id/{id}")
    public Set<Product> getProductByCategort(@PathVariable Long id){
        return categoryService.getProductCategory(id);
    }


}
