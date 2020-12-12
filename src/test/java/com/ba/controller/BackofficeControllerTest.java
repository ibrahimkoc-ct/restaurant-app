package com.ba.controller;



import static org.junit.jupiter.api.Assertions.*;

import com.ba.builder.CategoryBuilder;
import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.converter.BackofficeDtoConverter;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import com.ba.service.BackofficeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;
import java.util.function.Supplier;

import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class BackofficeControllerTest {

    @InjectMocks
    private BackofficeController controller;

    @Mock
    private BackofficeService service;
    CategoryBuilder categoryBuilder = new CategoryBuilder();
    Category category=categoryBuilder.id(1L).description("pizza").imageToUrl("no image").name("Pizza").build();
    Set<Category> categories= new HashSet<>();
    List<CategoryDTO> categoriesDto= new ArrayList<>();
    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList =new ArrayList<>();
    Set<Product> productSet = new HashSet<>();
    ProductBuilder productBuilder = new ProductBuilder();
    Product product=productBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();
    ProductDTOBuilder productDTOBuilder= new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();



    @Test
    public void getAllProductBackofficeControllerTest(){
        list.add(product);
        Mockito.when(service.getAllProduct()).thenReturn(dtoList);
        List<ProductDTO> result =controller.getAllProduct();
        assertEquals(result,dtoList);
    }
    @Test
    public void deleteProductBackofficeControllerTest(){
        Long id=1L;
        when(service.deleteProduct(id)).thenReturn("ürün silindi");
        String result=controller.deleteProduct(id);
        assertEquals(result,"ürün silindi");
    }
    @Test
    public void updateProductBackofficeControllerTest(){
        Long id=1L;
        Mockito.when(service.updateProduct(id,productDTO)).thenReturn(productDTO);
        ProductDTO result =controller.updateProduct(id,productDTO);
        assertEquals(result,productDTO);
    }
    @Test
    public void getProductByIdBackofficeControllerTest(){
        Long id=1L;
        when(service.getProductById(id)).thenReturn(productDTO);
        ProductDTO result=controller.getProductById(id);
        assertEquals(result,productDTO);

    }
    @Test
    public void addProductIdBackofficeContollerTest(){
        Long id=1L;
        Mockito.when(service.addProductId(productDTO,id)).thenReturn("kisi eklendi");
        String result=controller.addProductId(productDTO,id);
        assertEquals(result,"product");
    }
}