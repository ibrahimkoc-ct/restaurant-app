package com.ba.service;

import com.ba.converter.ClientDtoConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ProductRepository repository;
    //yapıldı
    public List<ProductDTO> getAllProduct(){
        List<Product> clientList = repository.findAll();
       return ClientDtoConverter.clientListToClienDTOList(clientList);
    }

    public ProductDTO getProductById(Long id){
        Optional<Product> dtoList =repository.findById(id);
        return ClientDtoConverter.clientDTOgetById(dtoList);

    }


    public List<ProductDTO> listSelectedCategory(String categoryName){

       repository.findCategoryByName(ClientDtoConverter.clientDTOdeleteProduct(categoryName));
       return getAllProduct();
    }


}
