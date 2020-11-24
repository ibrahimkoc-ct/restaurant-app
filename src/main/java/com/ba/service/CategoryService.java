package com.ba.service;

import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.entity.Users;
import com.ba.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        categoryRepository.findAll();
        return categoryRepository.findAll();
    }

    public List<Category> deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }
    public List<Category> addCategory(Category category) {

        categoryRepository.save(category);
        return categoryRepository.findAll();
    }
    public List<Category> updateCategory (Category category){
        categoryRepository.saveAndFlush(category);
        return getAllCategory();
    }
    public Optional<Category> getCategoryById(Long id) {

        return categoryRepository.findById(id);
    }
    public Set<Product> getProductCategory(Long id){
        Optional<Category> category=categoryRepository.findById(id);
        return category.get().getProducts();

    }


}
