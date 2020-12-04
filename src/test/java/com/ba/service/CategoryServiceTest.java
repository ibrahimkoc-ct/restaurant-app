package com.ba.service;


import com.ba.converter.CategoryDtoConventer;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

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
    public void shouldCategoryList(){
        list.add(category);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<CategoryDTO> dto= CategoryDtoConventer.categoryDTOListToCategory(list);
        List<CategoryDTO> categoryDTOList= service.getAllCategory();
        assertEquals(dto.get(0).getId(),categoryDTOList.get(0).getId());

    }

    @Test
    public void shouldCategoryAddCategoryDTO(){
        Mockito.when(repository.save(any())).thenReturn(category);
        String result =service.addCategory(categoryDTO);
        assertEquals(result,"kisi eklendi");

    }

    @Test(expected =RuntimeException.class)
    public void shouldDeleteCategoryById(){
        Long id=1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result =service.deleteCategory(id);
        assertEquals(result,"kisi silindi");
        verify(repository,times(1)).deleteById(id);
    }
    @Test
    public void shouldUpdateCategory(){
        Mockito.when(repository.saveAndFlush(category)).thenReturn(category);
        CategoryDTO result =service.updateCategory(categoryDTO);
        assertEquals(result,categoryDTO);

    }
    @Test
    public void shouldGetCategoryById(){
    Long id=1L;
    Optional<Category> categoryList =Optional.of(category);
    Mockito.when(repository.findById(id)).thenReturn(categoryList);
    CategoryDTO result=service.getCategoryById(id);
    assertEquals(result.getId(),id);
    }

    @Test
    public void shouldgetProductCategory(){
        setProduct.add(product);
        category.setProducts(setProduct);
        dtos.add(productDTO);
        Long id=1L;

        Optional<Category> categoryList =Optional.of(category);
        Mockito.when(repository.findById(id)).thenReturn(categoryList);
        Set<ProductDTO> result=CategoryDtoConventer.categoryDTOgetProductCategory(categoryList);

        Set<ProductDTO> dtoset= service.getProductCategory(id);
        assertEquals(result.iterator().next().getId(),dtoset.iterator().next().getId());
     }
}