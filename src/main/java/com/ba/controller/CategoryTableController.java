package com.ba.controller;


import com.ba.dto.CategoryTableDTO;
import com.ba.entity.Category;
import com.ba.entity.CategoryTable;
import com.ba.entity.Product;

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
    public String addCategory(@RequestBody CategoryTableDTO category) {
         repository.addCategory(category);
         return "category eklendi";
    }
    @GetMapping("/list")
    public List<CategoryTableDTO> getAllCategory() {
        return repository.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
         repository.deleteCategory(id);
         return "category silindi";
    }
    @GetMapping("/id/{id}")
    public CategoryTableDTO getCategoryById(@PathVariable Long id){
        return repository.getCategoryById(id);
    }
    @PutMapping("/update")
    public CategoryTableDTO updateCategory(@RequestBody CategoryTableDTO category){
         repository.updateCategory(category);
         return category;
    }


}
