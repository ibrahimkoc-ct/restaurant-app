package com.ba.service;

import com.ba.entity.Category;
import com.ba.entity.CategoryTable;
import com.ba.entity.Product;
import com.ba.entity.RestaurantTable;
import com.ba.repository.CategoryTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryTableService {
    @Autowired
    CategoryTableRepository repository;
    public List<CategoryTable> getAllCategory() {
        repository.findAll();
        return repository.findAll();
    }

    public List<CategoryTable> deleteCategory(Long id) {
        repository.deleteById(id);
        return repository.findAll();
    }
    public List<CategoryTable> addCategory(CategoryTable category) {

        repository.save(category);
        return repository.findAll();
    }
    public List<CategoryTable> updateCategory (CategoryTable category){
        repository.saveAndFlush(category);
        return getAllCategory();
    }
    public Optional<CategoryTable> getCategoryById(Long id) {

        return repository.findById(id);
    }
    public Set<RestaurantTable> getProductCategory(Long id){
        Optional<CategoryTable> category=repository.findById(id);
        return category.get().getRestaurantTables();

    }
}
