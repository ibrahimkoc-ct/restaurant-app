package com.ba.service;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
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
        Customer customer=repository.save(mapper.toEntity(dto));
        dto.setId(customer.getId());
        return dto;
    }

    public CustomerDTO updateCustomer(CustomerDTO dto) {
       Customer customer= repository.saveAndFlush(mapper.toEntity(dto));
       //degisenleri sorgula
       dto.setId(customer.getId());
        return dto;
    }

    public CustomerDTO customerDTOById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (!customer.isPresent()) {
            return null;
        }
        return CustomerMapper.INSTANCE.toDTO(customer.get());
    }

    public Page<CustomerDTO> getPageCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (pageable == null) {
            return null;
        }
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Slice<CustomerDTO> getSliceCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (pageable == null) {
            return null;
        }
        return repository.findAllBy(pageable).map(mapper::toDTO);
    }
}
