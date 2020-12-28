package com.ba.controller;

import com.ba.builder.CategoryBuilder;
import com.ba.builder.CategoryDTOBuilder;
import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {
    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryService service;
    CategoryBuilder categoryBuilder = new CategoryBuilder();
    CategoryDTOBuilder categoryDTOBuilder = new CategoryDTOBuilder();
    Category category = categoryBuilder.name("Pizza").description("pizza").id(1L).build();
    CategoryDTO categoryDTO = categoryDTOBuilder.name("Pizza").description("pizza").id(1L).build();
    ProductBuilder productBuilder = new ProductBuilder();
    Product product = productBuilder.description("pizza").id(1L).price("15").title("Pizza").build();
    ProductDTOBuilder productDTOBuilder = new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.description("pizza").id(1L).price("15").title("Pizza").build();

    List<Category> list = new ArrayList<>();
    List<CategoryDTO> dtoList = new ArrayList<>();
    Set<ProductDTO> dtos = new HashSet<>();
    Set<Product> setProduct = new HashSet<>();


    @Test
    public void addCategoryCategoryControllerTest() {
        categoryDTO.setId(null);
        when(service.addCategory(categoryDTO)).thenReturn("kisi eklendi");
        CategoryDTO result = controller.addCategory(categoryDTO);
        assertEquals(result, categoryDTO);
    }

    @Test
    public void getAllCategoryCategoryControllerTest() {
        when(service.getAllCategory()).thenReturn(dtoList);
        List<CategoryDTO> result = controller.getAllCategory();
        assertEquals(result, dtoList);
    }

    @Test
    public void deleteCategoryCategoryControllerTest() {
        Long id = 1L;
        when(service.deleteCategory(id)).thenReturn("kisi silindi");
        String result = controller.deleteCategory(1L, "deneme");
        assertEquals(result, "Kategori silindi");
    }

    @Test
    public void getCategoryByIdCategoryControllerTest() {
        Long id = 1L;
        when(service.getCategoryById(id)).thenReturn(categoryDTO);
        CategoryDTO result = controller.getCategoryById(id);
        assertEquals(result, categoryDTO);
    }

    @Test
    public void updateCategoryCategoryControllerTest() {
        when(service.updateCategory(categoryDTO)).thenReturn(categoryDTO);
        CategoryDTO result = controller.updateCategory(categoryDTO);
        assertEquals(result, categoryDTO);

    }

    @Test
    public void getProductByCategoryCategoryControllerTest() {
        Long id = 1L;
        when(service.getCategoryById(id)).thenReturn(categoryDTO);
        List<ProductDTO> result = controller.getProductByCategort(id);
        assertNotNull(result);
    }

    @Test(expected = BussinessRuleException.class)
    public void addCategoryIdNullTest() {
        controller.addCategory(categoryDTO);
    }

    @Test(expected = BussinessRuleException.class)
    public void addCategoryNullTest() {
        controller.addCategory(null);
    }
    @Test(expected = BussinessRuleException.class)
    public void updateCategoryIdNullTest() {
        categoryDTO.setId(null);
        controller.updateCategory(categoryDTO);
    }

    @Test(expected = BussinessRuleException.class)
    public void updateCategoryNullTest() {
        controller.updateCategory(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteCategoryIdNullTest() {
        controller.deleteCategory(null, "null");
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteCategoryIdTest() {
        controller.deleteCategory(-1L, "null");
    }
    @Test(expected = BussinessRuleException.class)
    public void getCategoryIdNullTest() {
        controller.getCategoryById(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getCategoryIdTest() {
        controller.getCategoryById(-1L);
    }
    @Test(expected = BussinessRuleException.class)
    public void getProductByCategoryIdNullTest() {
        controller.getProductByCategort(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getProductByCategoryIdTest() {
        controller.getProductByCategort(-1L);
    }

}