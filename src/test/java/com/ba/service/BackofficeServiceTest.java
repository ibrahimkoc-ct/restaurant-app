package com.ba.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ba.builder.*;
import com.ba.converter.BackofficeDtoConverter;
import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class BackofficeServiceTest {

    @InjectMocks
    private BackofficeService service;


    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository repository;

    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList =new ArrayList<>();
    List<CategoryDTO> categoryDTO =new ArrayList<>();
    List<Category> categoryList =new ArrayList<>();
    Set<Product> productSet = new HashSet<>();
    MediaBuilder mediaBuilder=new MediaBuilder();
    Media media=mediaBuilder.name("name").id(1L).build();
    MediaDTOBuilder mediaDTOBuilder= new MediaDTOBuilder();
    MediaDTO mediaDTO= mediaDTOBuilder.id(1L).name("name").build();
    CategoryBuilder categoryBuilder = new CategoryBuilder();
    CategoryDTOBuilder categoryDTOBuilder= new CategoryDTOBuilder();
    Category category=categoryBuilder.id(1L).description("pizza").imageToUrl("no image").name("Pizza").media(media).build();
    ProductBuilder productBuilder = new ProductBuilder();
    Product product=productBuilder.category("Pizza").description("pizza").id(1L).media(media).price("15").title("Pizza").categories(categoryList).urlToImage("no image").build();
    ProductDTOBuilder productDTOBuilder= new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.category("Pizza").mediaDTO(mediaDTO).description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").build();
    CategoryDTO dto=categoryDTOBuilder.id(1L).description("pizza").imageToUrl("no image").name("Pizza").mediaDTO(mediaDTO).build();





    @Test(expected =RuntimeException.class)
    public void shouldDeleteProductById(){
        Long id=1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result=service.deleteProduct(id);
        assertEquals(result,"kisi silindi");
        verify(repository,times(1)).deleteById(id);

    }
    @Test
    public void shouldBackOfficeProductList() {
        list.add(product);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<ProductDTO> dto= BackofficeDtoConverter.productListTOProductList(list);
        List<ProductDTO> productsList =service.getAllProduct();
        assertNotNull(dto);
        assertNotNull(productsList);
        assertEquals(dto.get(0).getId(),productsList.get(0).getId());

    }

    @Test
    public void shouldUpdateBackofficeProductBy(){
        categoryList.add(category);
        product.setCategories(categoryList);
        categoryDTO.add(dto);
        productDTO.setCategories(categoryDTO);
        dto.setProducts(dtoList);
        category.setProducts(list);
        Mockito.when(repository.saveAndFlush(product)).thenReturn(product);
        ProductDTO result=service.updateProduct(1L,productDTO);
        assertEquals(result,productDTO);
    }

    @Test
    public void shouldGetProductByID(){
        Optional<Product> list =Optional.of(product);
        Mockito.when(repository.findById(1L)).thenReturn(list);
        ProductDTO dto=service.getProductById(1L);
        assertNotNull(dto);
        assertEquals(dto.getId(),list.get().getId());
    }

    @Test
    public void shouldAddNewBackOfficeProduct(){
        Long id=1L;
        productSet.add(product);
        category.setProducts(list);
        productDTO.setCategories(categoryDTO);
        Optional<Category> optionalCategory =Optional.of(category);
        Mockito.when(categoryRepository.findById(id)).thenReturn(optionalCategory);
        Mockito.when(categoryRepository.save(any())).thenReturn(category);
        Product product=BackofficeDtoConverter.addProductIDtoDto(categoryList,productDTO);
        String result=service.addProductId(productDTO,id);
        assertEquals(result,"kisi eklendi");

    }
}