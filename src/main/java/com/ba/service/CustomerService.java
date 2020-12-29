package com.ba.service;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CustomerMapper;
import com.ba.mapper.MediaMapper;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    CustomerMapper mapper;

    public String deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
        return "müsteri silindi";
    }

    public CustomerDTO addCustomer(@RequestBody CustomerDTO dto) {
        Customer customer = mapper.toEntity(dto);
        repository.save(customer);
        return dto;
    }

    public CustomerDTO updateCustomer(CustomerDTO dto) {
        Optional<Customer> optionalCustomer = repository.findById(dto.getId());
        if (optionalCustomer==null) {
            throw new SystemException("Customer not found in database");
        }
        UpdateHelper.updateCustomerHelper(dto, optionalCustomer);
        repository.saveAndFlush(optionalCustomer.get());
        return mapper.toDTO(optionalCustomer.get());
    }

    public CustomerDTO customerDTOById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer==null) {
            throw new SystemException("Customer not found in database");
        }
        return mapper.toDTO(customer.get());
    }

    public Page<CustomerDTO> getPageCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerDTO> dtoPage = repository.findAll(pageable).map(mapper::toDTO);
        return dtoPage;
    }

    public Slice<CustomerDTO> getSliceCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<CustomerDTO> customerDTO = repository.findAllBy(pageable).map(mapper::toDTO);
        return customerDTO;
    }
    public List<CustomerDTO> getAllCustomer(){
        List<Customer> customers =repository.findAll();
        if(customers==null){
            throw  new SystemException("Customer not found in database");
        }
        customers.forEach((customer -> customer.setMedia(null)));
        return mapper.toDTOList(customers);
    }
}
