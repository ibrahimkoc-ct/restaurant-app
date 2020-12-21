package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public CategoryDTO addCategory(@RequestBody CategoryDTO category) {
         categoryService.addCategory(category);
         return category;
    }
    @GetMapping("/list")
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
         categoryService.deleteCategory(id);
         return "category silindi";
    }
    @GetMapping("/id/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/update")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO category){
         categoryService.updateCategory(category);
         return category;
    }

    @GetMapping("/product/id/{id}")
    public List<ProductDTO> getProductByCategort(@PathVariable Long id){
        return categoryService.getProductCategory(id);
    }


}
