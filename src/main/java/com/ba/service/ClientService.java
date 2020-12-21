package com.ba.service;

import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import com.ba.mapper.ProductMapper;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ProductRepository repository;
    public List<ProductDTO> getAllProduct(){
        List<Product> clientList = repository.findAll();
        return ProductMapper.INSTANCE.toDTOList(clientList);
    }

    public ProductDTO getProductById(Long id){
        Product dto=repository.findById(id).get();
        return ProductMapper.INSTANCE.toDTO(dto);
    }

    public List<ProductDTO> listSelectedCategory(String categoryName){
       repository.findCategoryByName(categoryName);
       return getAllProduct();
    }


}
