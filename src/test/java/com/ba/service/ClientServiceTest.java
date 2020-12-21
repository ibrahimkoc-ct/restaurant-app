package com.ba.service;

import com.ba.builder.ProductBuilder;
import com.ba.builder.ProductDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Media;
import com.ba.entity.Product;
import com.ba.mapper.ProductMapper;
import com.ba.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private ProductRepository repository;
    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList = new ArrayList<>();
    Media media = new Media();
    MediaDTO mediaDTO = new MediaDTO();

    ProductBuilder productBuilder = new ProductBuilder();
    Product product = productBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").urlToImage("no image").media(media).build();
    ProductDTOBuilder productDTOBuilder = new ProductDTOBuilder();
    ProductDTO dto = productDTOBuilder.category("Pizza").description("pizza").id(1L).price("15").title("Pizza").mediaDTO(mediaDTO).urlToImage("no image").build();


    @Test
    public void shouldClientProductList() {
        list.add(product);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<ProductDTO> dto = ProductMapper.INSTANCE.toDTOList(list);
        List<ProductDTO> productsList = service.getAllProduct();
        assertNotNull(dto);
        assertNotNull(productsList);
        assertEquals(dto.get(0).getId(), productsList.get(0).getId());
    }

    @Test
    public void shouldGetProductByID() {
        Optional<Product> list = Optional.of(product);
        Mockito.when(repository.findById(1L)).thenReturn(list);
        ProductDTO dto = service.getProductById(1L);
        assertNotNull(dto);
        assertEquals(dto.getId(), list.get().getId());
    }

    @Test
    public void shouldListSelectedCategory() {
        list.add(product);
        String categoryName = "pizza";
        Mockito.when(repository.findAll()).thenReturn(list);
        List<ProductDTO> productsList = service.listSelectedCategory(categoryName);
        assertEquals(list.get(0).getId(), productsList.get(0).getId());

    }

}