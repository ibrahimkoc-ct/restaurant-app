package com.ba.controller;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public CategoryDTO addCategory(@RequestBody CategoryDTO category) {
        if (category == null || category.getId()!=null) {
            throw new BussinessRuleException("Category cannot be empty!");
        }
        categoryService.addCategory(category);
        return category;
    }

    @GetMapping("/list")
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        categoryService.deleteCategory(id);
        return "category silindi";
    }

    @GetMapping("/id/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/update")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO category) {
        if (category == null ||category.getId()==null) {
            throw new BussinessRuleException("Category cannot be empty!");
        }
        categoryService.updateCategory(category);
        return category;
    }

    @GetMapping("/product/id/{id}")
    public List<ProductDTO> getProductByCategort(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return categoryService.getProductCategory(id);
    }


}
