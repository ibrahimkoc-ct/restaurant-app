package com.ba.controller;

import com.ba.builder.ProductSalesBuilder;
import com.ba.builder.ProductSalesDTOBuilder;
import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;
import com.ba.exception.BussinessRuleException;
import com.ba.service.ProductSalesService;
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


@RunWith(MockitoJUnitRunner.class)
public class ProductSalesControllerTest {

    @InjectMocks
    private ProductSalesController controller;

    @Mock
    private ProductSalesService service;
    List<ProductSales> sales = new ArrayList<>();
    List<ProductSalesDTO> dto = new ArrayList<>();
    ProductSalesBuilder productBuilder = new ProductSalesBuilder();
    ProductSalesDTOBuilder productDTOBuilder = new ProductSalesDTOBuilder();
    ProductSales s = productBuilder.id(1L).productId(1L).piece(15L).price(21L).selectedtable("15").title("asd").waiterName("ibrahim").build();
    ProductSalesDTO productSalesDTO = productDTOBuilder.id(1L).productId(1L).piece(15L).price(21L).selectedtable("15").title("asd").waiterName("ibrahim").build();

    @Before
    public void setUp() throws Exception {
        sales.add(s);
        dto.add(productSalesDTO);

    }

    @Test
    public void addProductSalesControllerTest() {
        Mockito.when(service.addProductSales(dto)).thenReturn("Product Sales Eklendi");
        String res = controller.addProductSales(dto);
        assertNotNull(res);
        assertEquals(res, "Product Sales Eklendi");

    }

    @Test
    public void getAllProductSalesControllerTest() {
        Mockito.when(service.getAllProductSales()).thenReturn(dto);
        List<ProductSalesDTO> result = controller.getAllProduct();
        assertEquals(result, dto);
    }
}