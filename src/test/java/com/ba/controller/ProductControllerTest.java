package com.ba.controller;

import com.ba.builder.CategoryBuilder;
import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.exception.BussinessRuleException;
import com.ba.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;
    CategoryBuilder categoryBuilder = new CategoryBuilder();
    Category category=categoryBuilder.id(1L).description("pizza").name("Pizza").build();
    Set<Category> categories= new HashSet<>();
    List<CategoryDTO> categoriesDto= new ArrayList<>();
    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList =new ArrayList<>();
    Set<Product> productSet = new HashSet<>();
    ProductBuilder productBuilder = new ProductBuilder();
    Product product=productBuilder.description("pizza").id(1L).price("15").title("Pizza").build();
    ProductDTOBuilder productDTOBuilder= new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.description("pizza").id(1L).price("15").title("Pizza").build();


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
        productDTO.setId(null);
        Mockito.when(service.addProductId(productDTO)).thenReturn("kisi eklendi");
        String result=controller.addProductId(productDTO);
        assertEquals(result,"product");
    }
    @Test
    public void getPageProductControllerTest(){
        dtoList.add(productDTO);
        Page<ProductDTO> page = new PageImpl<>(dtoList);
        Mockito.when(service.getPageProduct(1,10)).thenReturn(page);
        Page<ProductDTO> result =controller.searchProduct(1,10);
        assertEquals(result,page);
    }
    @Test
    public void getSliceProductControllerTest(){
        dtoList.add(productDTO);
        Slice<ProductDTO> slice = new SliceImpl<>(dtoList);
        Mockito.when(service.loadMoreProduct(1L,1,10)).thenReturn(slice);
        Slice<ProductDTO> result =controller.loadMoreProduct(1L,1,10);
        assertEquals(result,slice);
    }
    @Test(expected = BussinessRuleException.class)
    public void addProductIdNullTest() {
        controller.addProductId(productDTO);
    }


    @Test(expected = BussinessRuleException.class)
    public void updateProductIdNullTest() {
        productDTO.setId(null);
        controller.updateProduct(null,productDTO);
    }

    @Test(expected = BussinessRuleException.class)
    public void getProductIdNullTest() {
        controller.getProductById(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getProductIdTest() {
        controller.getProductById(-1L);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteProductIdNullTest() {
        controller.deleteProduct(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteProductIdTest() {
        controller.deleteProduct(-1L);
    }

}