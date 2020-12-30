package com.ba.controller;


import com.ba.dto.CategoryTableDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CategoryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorytable")
public class CategoryTableController {
    @Autowired
    CategoryTableService repository;
    @PostMapping("/add")
    public String addCategory(@Valid @RequestBody CategoryTableDTO category) {
        if ( category.getId() !=null) {
            throw new BussinessRuleException("Category cannot be empty!");
        }
         repository.addCategory(category);
         return "category eklendi";
    }
    @GetMapping("/list")
    public List<CategoryTableDTO> getAllCategory() {
        return repository.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
         repository.deleteCategory(id);
         return "category silindi";
    }
    @GetMapping("/id/{id}")
    public CategoryTableDTO getCategoryById(@PathVariable Long id){
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return repository.getCategoryById(id);
    }
    @PutMapping("/update")
    public CategoryTableDTO updateCategory(@Valid @RequestBody CategoryTableDTO category){
        if (category.getId() == null || category.getId()<0) {
            throw new BussinessRuleException("Category Id cannot be empty!");
    }
         repository.updateCategory(category);
         return category;
    }


}
