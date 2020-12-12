package com.ba.service;


import com.ba.builder.*;
import com.ba.converter.BackofficeDtoConverter;
import com.ba.converter.CategoryDtoConventer;
import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
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

    List<Category> list = new ArrayList<>();
    List<CategoryDTO> dtoList = new ArrayList<>();
    List<ProductDTO> dtos= new ArrayList<>();
    List<Product> setProduct= new ArrayList<>();
    CategoryBuilder categoryBuilder= new CategoryBuilder();
    CategoryDTOBuilder categoryDTOBuilder= new CategoryDTOBuilder();
    MediaBuilder mediaBuilder=new MediaBuilder();
    Media media=mediaBuilder.name("name").id(1L).build();
    MediaDTOBuilder mediaDTOBuilder= new MediaDTOBuilder();
    MediaDTO mediaDTO= mediaDTOBuilder.id(1L).name("name").build();

    ProductBuilder productBuilder = new ProductBuilder();
    Product product=productBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").media(media).build();
    ProductDTOBuilder productDTOBuilder= new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").mediaDTO(mediaDTO).build();
    Category category = categoryBuilder.name("Pizza").imageToUrl("no image").description("pizza").id(1L).products(setProduct).media(media).build();
    CategoryDTO categoryDTO = categoryDTOBuilder.name("Pizza").imageToUrl("no image").description("pizza").products(dtos).mediaDTO(mediaDTO).id(1L).build();


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldCategoryList(){
        list.add(category);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<CategoryDTO> dto= CategoryDtoConventer.convertListToDTOList(list);
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
        list.add(category);
        dtoList.add(categoryDTO);
        Optional<Category> categoryList =Optional.of(category);
        Mockito.when(repository.findById(1L)).thenReturn(categoryList);
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
        List<ProductDTO> result= BackofficeDtoConverter.productListTOProductList(setProduct);

        Set<ProductDTO> dtoset= service.getProductCategory(id);
        assertEquals(result.iterator().next().getId(),dtoset.iterator().next().getId());
     }
}