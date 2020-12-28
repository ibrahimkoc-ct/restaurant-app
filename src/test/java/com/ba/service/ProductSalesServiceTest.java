package com.ba.service;


import com.ba.builder.ProductSalesBuilder;
import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;
import com.ba.exception.SystemException;
import com.ba.repository.ProductSalesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductSalesServiceTest {

    @InjectMocks
    private ProductSalesService salesService;

    @Mock
    private ProductSalesRepository repository;
    List<ProductSales> sales = new ArrayList<>();
    List<ProductSalesDTO> dto = new ArrayList<>();
    ProductSalesBuilder productBuilder = new ProductSalesBuilder();
    ProductSales s = productBuilder.id(1L).productId(15L).piece(15L).price(21L).selectedtable("15").title("asd").waiterName("ibrahim").build();

    @Before
    public void setUp() throws Exception {
        sales.add(s);

    }

    @Test
    public void shouldAddNewProductSales() {
        Mockito.when(repository.saveAll(any())).thenReturn(sales);
        String res = salesService.addProductSales(dto);
        assertNotNull(res);
        assertEquals(res, "kullanıcı eklendi");

    }

    @Test
    public void shouldProductSalesList() {
        Mockito.when(repository.findAll()).thenReturn(sales);
        List<ProductSalesDTO> list = salesService.getAllProductSales();
        assertNotNull(list);
        assertNotNull(sales);
    }
    @Test(expected = SystemException.class)
    public void getAllProductSalesNullTest(){
        Mockito.when(repository.findAll()).thenReturn(null);
        salesService.getAllProductSales();
    }


}