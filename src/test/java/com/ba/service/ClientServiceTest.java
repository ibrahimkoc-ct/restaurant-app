package com.ba.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ba.converter.ClientDtoConverter;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.repository.ProductRepository;
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
public class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private ProductRepository repository;
    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList =new ArrayList<>();

    Product product = new Product();


    @Before
    public void setUp() throws Exception {
        product.setId(1L);
        product.setCategory("Pizza");
        product.setUrlToImage("No name");
        product.setPrice("15");
        product.setTitle("pizza");
        product.setDescription("Pizza");
    }

    @Test
    public void shouldClientProductList(){
        list.add(product);
    Mockito.when(repository.findAll()).thenReturn(list);
    List<ProductDTO> dto= ClientDtoConverter.clientListToClienDTOList(list);
    List<ProductDTO> productsList =service.getAllProduct();
    assertNotNull(dto);
    assertNotNull(productsList);
    assertEquals(dto.get(0).getId(),productsList.get(0).getId());
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
    public void shouldListSelectedCategory(){
        list.add(product);
        String categoryName="pizza";
        Mockito.when(repository.findAll()).thenReturn(list);
        List<ProductDTO> productsList =service.listSelectedCategory(categoryName);
        assertEquals(list.get(0).getId(),productsList.get(0).getId());

    }

}