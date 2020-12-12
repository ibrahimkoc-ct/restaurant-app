package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.ba.builder.CategoryBuilder;
import com.ba.builder.CategoryDTOBuilder;
import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {
    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryService service;
    CategoryBuilder categoryBuilder= new CategoryBuilder();
    CategoryDTOBuilder categoryDTOBuilder= new CategoryDTOBuilder();
    Category category = categoryBuilder.name("Pizza").imageToUrl("no image").description("pizza").id(1L).build();
    CategoryDTO categoryDTO = categoryDTOBuilder.name("Pizza").imageToUrl("no image").description("pizza").id(1L).build();
    ProductBuilder productBuilder = new ProductBuilder();
    Product product=productBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();
    ProductDTOBuilder productDTOBuilder= new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();

    List<Category> list = new ArrayList<>();
    List<CategoryDTO> dtoList = new ArrayList<>();
    Set<ProductDTO> dtos= new HashSet<>();
    Set<Product> setProduct= new HashSet<>();


    @Test
    public void addCategoryCategoryControllerTest(){
        when(service.addCategory(categoryDTO)).thenReturn("kisi eklendi");
        CategoryDTO result=controller.addCategory(categoryDTO);
        assertEquals(result,categoryDTO);
    }
    @Test
    public void getAllCategoryCategoryControllerTest(){
        when(service.getAllCategory()).thenReturn(dtoList);
        List<CategoryDTO> result=controller.getAllCategory();
        assertEquals(result,dtoList);
    }
    @Test
    public void deleteCategoryCategoryControllerTest(){
        Long id =1L;
        when(service.deleteCategory(id)).thenReturn("kisi silindi");
        String result=controller.deleteCategory(id);
        assertEquals(result,"category silindi");
    }
     @Test
    public void getCategoryByIdCategoryControllerTest(){
        Long id=1L;
        when(service.getCategoryById(id)).thenReturn(categoryDTO);
        CategoryDTO result =controller.getCategoryById(id);
        assertEquals(result,categoryDTO);
     }
     @Test
    public void updateCategoryCategoryControllerTest(){
        when(service.updateCategory(categoryDTO)).thenReturn(categoryDTO);
        CategoryDTO result=controller.updateCategory(categoryDTO);
        assertEquals(result,categoryDTO);

     }
     @Test
    public void getProductByCategoryCategoryControllerTest(){
         Long id=1L;
        when(service.getCategoryById(id)).thenReturn(categoryDTO);
        Set<ProductDTO> result= controller.getProductByCategort(id);
        assertNotNull(result);
     }




}