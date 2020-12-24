package com.ba.controller;

import com.ba.dto.CustomerDTO;

import com.ba.entity.Customer;
import com.ba.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (id == null) {
            return null;
        }
        return service.deleteCustomer(id);
    }

    @PostMapping()
    public CustomerDTO addCustomer(@RequestBody CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        return service.addCustomer(dto);
    }

    @PutMapping()
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        return service.updateCustomer(dto);
    }

    @GetMapping("/{id}")
    public CustomerDTO customerDTOById(@PathVariable Long id) {
        if (id == null) {
            return null;
        }
        return service.customerDTOById(id);
    }

    @GetMapping("/page")
    public Page<CustomerDTO> getPageCustomers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        if (page == null || size == null) {
            return null;
        }
        return service.getPageCustomers(page, size);
    }

    @GetMapping("/slice")
    public Slice<CustomerDTO> getSliceCustomers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        if (page == null || size == null) {
            return null;
        }
        return service.getSliceCustomers(page, size);
    }

}
