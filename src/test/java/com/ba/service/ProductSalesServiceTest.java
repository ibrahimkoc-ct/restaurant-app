package com.ba.service;


import com.ba.dto.ProductSalesDTO;
import com.ba.entity.Product;
import com.ba.entity.ProductSales;
import com.ba.repository.ProductRepository;
import com.ba.repository.ProductSalesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class ProductSalesServiceTest {

    @InjectMocks
    private ProductSalesService salesService;

    @Mock
    private ProductSalesRepository repository;
    List<ProductSales> sales = new ArrayList<>();
    List<ProductSalesDTO> dto = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        ProductSales s = new ProductSales();
        s.setSelectedtable("masa 1");
        s.setPrice(12L);
        s.setPiece(25l);
        sales.add(s);

    }
    @Test
    public void shouldAddNewProductSales(){
        Mockito.when(repository.saveAll(any())).thenReturn(sales);
        String res=salesService.addProductSales(dto);
        assertNotNull(res);
        assertEquals(res,"kullanıcı eklendi");

    }
    @Test
    public void shouldProductSalesList(){
        Mockito.when(repository.findAll()).thenReturn(sales);
        List<ProductSalesDTO> list = salesService.getAllProductSales();
        assertNotNull(list);
        assertNotNull(sales);
    }




}