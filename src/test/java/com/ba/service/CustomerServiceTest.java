package com.ba.service;

import com.ba.builder.CustomerBuilder;
import com.ba.builder.CustomerDTOBuilder;
import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.entity.Role;
import com.ba.exception.SystemException;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    MediaBuilder mediaBuilder = new MediaBuilder();
    Media media = mediaBuilder.name("name").id(1L).build();
    MediaDTOBuilder mediaDTOBuilder = new MediaDTOBuilder();
    MediaDTO mediaDTO = mediaDTOBuilder.id(1L).name("name").build();
    CustomerDTOBuilder dtoBuilder = new CustomerDTOBuilder();
    CustomerDTO dto = dtoBuilder.address("address").id(1L).phoneNumber("13456").name("ibrahin").surname("koc").build();
    CustomerBuilder builder = new CustomerBuilder();
    Customer customer = builder.address("address").id(1L).phoneNumber("13456").name("ibrahin").surname("koc").build();


    @Test
    public void addCustomerTest() throws IOException {
        Mockito.when(repository.save(customer)).thenReturn(customer);
        CustomerDTO result =service.addCustomer(dto);
        assertEquals(result,dto);
    }
    @Test(expected = RuntimeException.class)
    public void deleteCustomerTest() throws IOException {
        Long id = 1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result=service.deleteCustomer(id);
        assertEquals(result,"musteri silindi");

    }
//    @Test
//    public void updateCustomerTest(){
//        when(mapper.toDTO(Mockito.any())).thenReturn(dto);
//        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customer));
//        Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(customer);
//        CustomerDTO result =service.updateCustomer(dto);
//        assertEquals(result,dto);
//    }

    @Test
    public void getPageCustomers(){
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        Pageable pageable =PageRequest.of(1,10);
        Page<Customer> page= new PageImpl<>(list);
        Mockito.when(repository.findAll(pageable)).thenReturn(page);
        Page<CustomerDTO> result =service.getPageCustomers(1,10);
        assertNotNull(result);
    }
    @Test
    public void getSliceCustomers(){
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        Pageable pageable =PageRequest.of(1,10);
        Slice<Customer> slice = new SliceImpl<>(list);
        Mockito.when(repository.findAllBy(pageable)).thenReturn(slice);
        Slice<CustomerDTO> result =service.getSliceCustomers(1,10);
        assertNotNull(result);
    }
//    @Test(expected = SystemException.class)
//    public void updateCustomerOptionalNull(){
//        when(repository.findById(1L)).thenReturn(null);
//        service.updateCustomer(dto);
//    }

    @Test(expected = Exception.class)
    public void getPageCustomersNull(){
        Pageable pageable=PageRequest.of(1,1);
        when(repository.findAll(pageable)).thenReturn(null);
        service.getPageCustomers(1,1);
    }
    @Test(expected = Exception.class)
    public void getSliceCustomersNull(){
        Pageable pageable=PageRequest.of(1,1);
        when(repository.findAllBy(pageable)).thenReturn(null);
        service.getSliceCustomers(1,1);
    }
    @Test(expected = SystemException.class)
    public void getAllCustomerNull() throws IOException, JAXBException {
        when(repository.findAll()).thenReturn(null);
        service.getAllCustomer();
    }
    @Test
    public void getAllCustomerTest() throws IOException, JAXBException {
        List<Customer> customers =new ArrayList<>();
        customers.add(customer);
        List<CustomerDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        when(mapper.toDTOList(customers)).thenReturn(dtoList);
        when(repository.findAll()).thenReturn(customers);
        List<CustomerDTO> result=service.getAllCustomer();
        assertNotNull(result);
    }
    @Test
    public void deleteCustomerById() throws IOException {
        String result =service.deleteCustomer(1L);
        assertEquals(result,"müsteri silindi");
        verify(repository).deleteById(1L);
    }
}