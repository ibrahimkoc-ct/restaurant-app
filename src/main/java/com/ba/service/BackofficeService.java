package com.ba.service;

import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.entity.User;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BackofficeService {
    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;


    public List<Product> getAllProduct() {
        repository.findAll();
        return repository.findAll();
    }

    public List<Product> deleteProduct(Long id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    public List<Product> addProduct(Product product) {

        repository.save(product);
        return repository.findAll();
    }

    public List<Product> updateProduct(long id, Product product) {
        Optional<Product> optionalArticle = repository.findAll().stream().filter(news1 -> news1.getId() == id).findAny();
        if (optionalArticle == null) {
            System.out.println("girilen ID ile haber bulunamadı!");
            return repository.findAll();
        }
        optionalArticle.get().setTitle(product.getTitle());
        optionalArticle.get().setPrice(product.getPrice());
        optionalArticle.get().setDescription(product.getDescription());
        repository.saveAndFlush(optionalArticle.get());

        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id) {

        return repository.findById(id);
    }

    public String categoryID(Long id) {
        Optional<Product> optionalArticle = repository.findAll().stream().filter(news1 -> news1.getId() == id).findAny();
        if (optionalArticle == null) {
            System.out.println("girilen ID ile haber bulunamadı!");

        }
      return optionalArticle.get().getCategory();
    }

    public void addProductId(Product product,Long id){
        Optional<Category> category =categoryRepository.findById(id);
        category.get().getProducts().add(product);
        categoryRepository.save(category.get());
    }




}