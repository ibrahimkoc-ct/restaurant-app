package com.ba.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ba.converter.BackofficeDtoConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
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
    Set<Product> productSet = new HashSet<>();
    Product product = new Product();
    ProductDTO productDTO = new ProductDTO();
    Category category = new Category();

    public BackofficeServiceTest() {
    }

    @Before
    public void setUp() throws Exception {
        product.setId(1L);
        product.setCategory("Pizza");
        product.setUrlToImage("No name");
        product.setPrice("15");
        product.setTitle("pizza");
        product.setDescription("Pizza");
        productDTO.setId(1L);
        productDTO.setCategory("Pizza");
        productDTO.setUrlToImage("No name");
        productDTO.setPrice("15");
        productDTO.setTitle("pizza");
        productDTO.setDescription("Pizza");
        category.setDescription("no");
        category.setName("pizza");
        category.setId(1l);
        category.setImageToUrl("no image");
    }

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
        category.setProducts(productSet);
        Optional<Category> list =Optional.of(category);
        Mockito.when(categoryRepository.findById(id)).thenReturn(list);
        Mockito.when(categoryRepository.save(any())).thenReturn(category);
        Category category1=BackofficeDtoConverter.addProductIDtoDto(list,productDTO);
        String result=service.addProductId(productDTO,id);
        assertEquals(result,"kisi eklendi");

    }
}