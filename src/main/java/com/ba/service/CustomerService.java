package com.ba.service;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.exception.SystemException;
import com.ba.helper.CustomerSoapHelper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    CustomerMapper mapper;

    @Transactional
    public String deleteCustomer(Long id) throws IOException {
        CustomerSoapHelper.deleteSoapCustomer(id);
        return "müsteri silindi";
    }
    @Transactional
    public CustomerDTO addCustomer(@RequestBody CustomerDTO dto) throws IOException {
        CustomerSoapHelper.addSoapCustomer(dto);
        return dto;
    }
    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO dto) throws IOException {
     CustomerSoapHelper.updateSoapCustomer(dto);

        return dto;
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
    public List<CustomerDTO> getAllCustomer() throws IOException, JAXBException {

        return CustomerSoapHelper.getAllCustomer();
    }
}
