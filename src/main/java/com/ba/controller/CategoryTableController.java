package com.ba.controller;


import com.ba.entity.Category;
import com.ba.entity.CategoryTable;
import com.ba.entity.Product;
import com.ba.entity.RestaurantTable;
import com.ba.service.CategoryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorytable")
public class CategoryTableController {
    @Autowired
    CategoryTableService repository;
    @PostMapping("/add")
    public List<CategoryTable> addCategory(@RequestBody CategoryTable category) {
        return repository.addCategory(category);
    }
    @GetMapping("/list")
    public List<CategoryTable> getAllCategory() {
        return repository.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public List<CategoryTable> deleteCategory(@PathVariable Long id) {
        return repository.deleteCategory(id);
    }
    @GetMapping("/id/{id}")
    public Optional<CategoryTable> getCategoryById(@PathVariable Long id){
        return repository.getCategoryById(id);
    }
    @PutMapping("/update")
    public List<CategoryTable> updateCategory(@RequestBody CategoryTable category){
        return repository.updateCategory(category);
    }
    @GetMapping("/table/id/{id}")
    public Set<RestaurantTable> getProductByCategort(@PathVariable Long id){
        return repository.getProductCategory(id);
    }


}
