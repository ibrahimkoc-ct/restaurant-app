package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.converter.ClientDtoConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
import com.ba.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ClientContollerTest {

    @InjectMocks
    private ClientContoller contoller;

    @Mock
    private ClientService service;
    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList =new ArrayList<>();

    ProductBuilder productBuilder = new ProductBuilder();
    Product product=productBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();
    ProductDTOBuilder productDTOBuilder= new ProductDTOBuilder();
    ProductDTO dto = productDTOBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();



    @Test
    public void getProductByIdControllerTest(){
        Optional<Product> list =Optional.of(product);
        Mockito.when(service.getProductById(1L)).thenReturn(dto);
        ProductDTO result=contoller.getProductById(1L);
        assertEquals(result,dto);
    }
    @Test
    public void getAllProductClientControllerTest(){
        list.add(product);
        Mockito.when(service.getAllProduct()).thenReturn(dtoList);
        List<ProductDTO> result=contoller.getAllProduct();
        assertEquals(result,dtoList);
    }
    @Test
    public void findCategoryClientControllerTest(){
        list.add(product);
        String categoryName="pizza";
        Mockito.when(service.listSelectedCategory(categoryName)).thenReturn(dtoList);
        List<ProductDTO> result =contoller.findCategory(categoryName);
        assertEquals(result,dtoList);
    }




}