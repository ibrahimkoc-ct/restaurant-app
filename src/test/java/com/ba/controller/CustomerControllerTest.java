package com.ba.controller;

import com.ba.builder.CustomerBuilder;
import com.ba.builder.CustomerDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController controller;

    @Mock
    private CustomerService service;
    CustomerDTOBuilder dtoBuilder = new CustomerDTOBuilder();
    CustomerDTO dto = dtoBuilder.address("address").id(1L).phoneNumber("13456").name("ibrahin").surname("koc").build();
    CustomerBuilder builder = new CustomerBuilder();
    Customer customer = builder.address("address").id(1L).phoneNumber("13456").name("ibrahin").surname("koc").build();

    @Test
    public void addCustomerControllerTest(){
        Mockito.when(service.addCustomer(dto)).thenReturn(dto);
        CustomerDTO result =controller.addCustomer(dto);
        assertEquals(result,dto);
    }
    @Test
    public void deleteCustomerControllerTest(){
        Long id =1L;
        Mockito.when(service.deleteCustomer(id)).thenReturn("müsteri silindi");
        String result =controller.deleteCustomer(id);
        assertEquals(result,"müsteri silindi");
    }
    @Test
    public void updateCustomerControllerTest(){
        Mockito.when(service.updateCustomer(dto)).thenReturn(dto);
        CustomerDTO result =controller.updateCustomer(dto);
        assertEquals(dto,result);
    }
    @Test
    public void getCustomerByIdTest(){
        Long id =1L;
        Mockito.when(service.customerDTOById(id)).thenReturn(dto);
        CustomerDTO result =controller.customerDTOById(id);
        assertEquals(result.getId(),id);
    }

}

