package com.ba.controller;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) throws IOException {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return service.deleteCustomer(id);
    }

    @PostMapping()
    public CustomerDTO addCustomer(@Valid @RequestBody CustomerDTO dto) throws IOException {
        if (dto.getId() !=null) {
            throw new BussinessRuleException("Customer cannot be empty!");
        }
        return service.addCustomer(dto);
    }

    @PutMapping()
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO dto) throws IOException {
        if (dto.getId() ==null) {
            throw new BussinessRuleException("Customer Id cannot be empty!");
        }
        return service.updateCustomer(dto);
    }

    @GetMapping("/page")
    public Page<CustomerDTO> getPageCustomers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return service.getPageCustomers(page, size);
    }

    @GetMapping("/slice")
    public Slice<CustomerDTO> getSliceCustomers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        return service.getSliceCustomers(page, size);
    }
    @GetMapping()
    public List<CustomerDTO> getAllCustomer() throws IOException, JAXBException {
        return service.getAllCustomer();
    }
}
