package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.ba.converter.CategoryDtoConventer;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;

import java.util.*;
import java.util.function.Supplier;

import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {
    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryService service;
    Category category = new Category();
    CategoryDTO categoryDTO = new CategoryDTO();
    List<Category> list = new ArrayList<>();
    List<CategoryDTO> dtoList = new ArrayList<>();
    Set<ProductDTO> dtos= new HashSet<>();
    Set<Product> setProduct= new HashSet<>();
    ProductDTO productDTO = new ProductDTO();
    Product product = new Product();

    @Before
    public void setUp() throws Exception {
        category.setId(1L);
        category.setImageToUrl("no image");
        category.setName("pizza");
        category.setDescription("no desc");
        categoryDTO.setId(1L);
        categoryDTO.setImageToUrl("no image");
        categoryDTO.setName("pizza");
        categoryDTO.setDescription("no desc");
        productDTO.setId(1L);
        productDTO.setDescription("no");
        productDTO.setTitle("pizza");
        product.setId(1L);
        product.setDescription("no");
        product.setTitle("pizza");
    }
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