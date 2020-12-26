package com.ba.service;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
        Customer customer = repository.save(mapper.toEntity(dto));
        dto.setId(customer.getId());
        return dto;
    }

    public CustomerDTO updateCustomer(CustomerDTO dto) {
        Optional<Customer> optionalCustomer = repository.findById(dto.getId());
        if (optionalCustomer.isEmpty()) {
            throw new SystemException("Customer not found in database");
        }
        UpdateHelper.updateCustomerHelper(dto, optionalCustomer);
        repository.saveAndFlush(optionalCustomer.get());
        return CustomerMapper.INSTANCE.toDTO(optionalCustomer.get());
    }

    public CustomerDTO customerDTOById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (!customer.isPresent()) {
            throw new SystemException("Customer not found in database");
        }
        return CustomerMapper.INSTANCE.toDTO(customer.get());
    }

    public Page<CustomerDTO> getPageCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerDTO> dtoPage = repository.findAll(pageable).map(mapper::toDTO);
        if (dtoPage.isEmpty()) {
            throw new SystemException("Customers Not found");
        }
        return dtoPage;
    }

    public Slice<CustomerDTO> getSliceCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<CustomerDTO> customerDTO = repository.findAllBy(pageable).map(mapper::toDTO);
        if (customerDTO.isEmpty()) {
            throw new SystemException("Customers Not found");
        }
        return customerDTO;
    }
}
