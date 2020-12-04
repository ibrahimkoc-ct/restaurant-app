package com.ba.service;

import com.ba.converter.BackofficeDtoConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.CategoryTable;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    public List<ProductDTO> getAllProduct() {
    List<Product> productList=repository.findAll();
    return BackofficeDtoConverter.productListTOProductList(productList);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(BackofficeDtoConverter.deleteProductDTOToProduct(id));
    }



    public void updateProduct(long id, ProductDTO product) {

        repository.saveAndFlush(BackofficeDtoConverter.updateProductDto(product));
    }

    public ProductDTO getProductById(Long id) {

        Optional<Product> dtoList =repository.findById(id);
        return BackofficeDtoConverter.productDTOgetbyID(dtoList);

    }


    public void addProductId(ProductDTO product,Long id){
        Optional<Category> category =categoryRepository.findById(id);

        categoryRepository.save(BackofficeDtoConverter.addProductIDtoDto(category,product));
    }



}