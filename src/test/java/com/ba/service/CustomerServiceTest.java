package com.ba.service;

import com.ba.builder.CustomerBuilder;
import com.ba.builder.CustomerDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.entity.Role;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    CustomerDTOBuilder dtoBuilder = new CustomerDTOBuilder();
    CustomerDTO dto = dtoBuilder.address("address").id(1L).phoneNumber("13456").name("ibrahin").surname("koc").build();
    CustomerBuilder builder = new CustomerBuilder();
    Customer customer = builder.address("address").id(1L).phoneNumber("13456").name("ibrahin").surname("koc").build();


    @Test
    public void addCustomerTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(customer);
        CustomerDTO result =service.addCustomer(dto);
        assertEquals(result,dto);
    }
    @Test(expected = RuntimeException.class)
    public void deleteCustomerTest(){
        Long id = 1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result=service.deleteCustomer(id);
        assertEquals(result,"musteri silindi");

    }
    @Test
    public void updateCustomerTest(){
        Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(customer);
        CustomerDTO result =service.updateCustomer(dto);
        assertEquals(result,dto);
    }
    @Test
    public void getCustomerByIdTest(){
        Long id=12l;
        customer.setId(id);
        Optional<Customer> optionalCustomer = Optional.of(customer);
        Mockito.when(repository.findById(id)).thenReturn(optionalCustomer);
        CustomerDTO result=service.customerDTOById(customer.getId());
        assertEquals(result.getId(),id);
    }

}