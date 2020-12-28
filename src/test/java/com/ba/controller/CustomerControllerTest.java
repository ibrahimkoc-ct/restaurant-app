package com.ba.controller;

import com.ba.builder.CustomerBuilder;
import com.ba.builder.CustomerDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;

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
        dto.setId(null);
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
    @Test
    public void getSliceCustomerControllerTest(){
        List<CustomerDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        Slice<CustomerDTO> dtoSlice=new SliceImpl<>(dtoList);
        Mockito.when(service.getSliceCustomers(1,10)).thenReturn(dtoSlice);
        Slice<CustomerDTO> result =controller.getSliceCustomers(1,10);
        assertEquals(result,dtoSlice);
    }
    @Test
    public void getPageCustomerControllerTest(){
        List<CustomerDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        Page<CustomerDTO> dtoPage=new PageImpl<>(dtoList);
        Mockito.when(service.getPageCustomers(1,10)).thenReturn(dtoPage);
        Page<CustomerDTO> result =controller.getPageCustomers(1,10);
        assertEquals(result,dtoPage);
    }
    @Test(expected = BussinessRuleException.class)
    public void deleteCustomerIdNullTest() {
        controller.deleteCustomer(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteCustomerIdTest() {
        controller.deleteCustomer(-1L);
    }
    @Test(expected = BussinessRuleException.class)
    public void addCustomerIdNullTest() {
        controller.addCustomer(dto);
    }

    @Test(expected = BussinessRuleException.class)
    public void addCustomerNullTest() {
        controller.addCustomer(null);
    }
    @Test(expected = BussinessRuleException.class)
    public void updateCustomerIdNullTest() {
        dto.setId(null);
        controller.updateCustomer(dto);
    }

    @Test(expected = BussinessRuleException.class)
    public void updateCustomerNullTest() {
        controller.updateCustomer(null);
    }
    @Test(expected = BussinessRuleException.class)
    public void getCustomerIdNullTest() {
        controller.customerDTOById(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getCustomerIdTest() {
        controller.customerDTOById(-1L);
    }

}

