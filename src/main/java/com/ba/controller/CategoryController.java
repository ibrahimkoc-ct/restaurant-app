package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
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
    public void addCategory(@RequestBody CategoryDTO category) {
         categoryService.addCategory(category);
    }
    @GetMapping("/list")
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
         categoryService.deleteCategory(id);
    }
    @GetMapping("/id/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/update")
    public void updateCategory(@RequestBody CategoryDTO category){
         categoryService.updateCategory(category);
    }

    @GetMapping("/product/id/{id}")
    public Set<ProductDTO> getProductByCategort(@PathVariable Long id){
        return categoryService.getProductCategory(id);
    }


}
