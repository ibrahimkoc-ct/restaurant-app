package com.ba.controller;

import com.ba.config.InternationalizationConfig;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CategoryService;
import liquibase.pro.packaged.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO category) {
        if (category.getId() != null) {
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
    public String deleteCategory(@PathVariable Long id, @RequestHeader("Accept-Language") String locale) {
        if (id == null || id < 0) {
            throw new BussinessRuleException(InternationalizationConfig.messageSource().getMessage("CategoryDeleteBussinessRuleException",null,new Locale(locale)));
        }
        categoryService.deleteCategory(id);
        return InternationalizationConfig.messageSource().getMessage("CategoryDelete",null,new Locale(locale));
    }

    @GetMapping("/id/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/update")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO category) {
        if (category.getId() == null) {
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

