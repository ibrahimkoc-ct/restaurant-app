package com.ba.service;

import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;
import com.ba.exception.SystemException;
import com.ba.mapper.ProductSalesMapper;
import com.ba.repository.ProductSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSalesService {
    @Autowired
    ProductSalesRepository productSalesrepository;

    public String addProductSales(List<ProductSalesDTO> productSalesDTO) {
        try {
            productSalesrepository.saveAll(ProductSalesMapper.INSTANCE.toEntityList(productSalesDTO));
            return "kullanıcı eklendi";
        } catch (Exception e) {
            throw new SystemException("System Failed");
        }
    }

    public List<ProductSalesDTO> getAllProductSales() {
        try {
            List<ProductSales> productlist = productSalesrepository.findAll();
            return ProductSalesMapper.INSTANCE.toDTOList(productlist);

        } catch (Exception e) {
            throw new SystemException("System Failed");
        }
    }
}
