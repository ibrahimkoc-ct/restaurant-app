package com.ba.service;

import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ProductRepository repository;

    public List<Product> getAllProduct(){
        repository.findAll();
        return repository.findAll();
    }
    public Optional<Product> getProductById(Long id){

        return repository.findById(id);
    }
    public List<Product> listSelectedCategory(String categoryName){
        return repository.findCategoryByName(categoryName);
    }
    public List<String> AllCategory(){
        return repository.getAllCategory();

    }

}
