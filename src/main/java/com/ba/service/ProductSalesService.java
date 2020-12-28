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
        productSalesrepository.saveAll(ProductSalesMapper.INSTANCE.toEntityList(productSalesDTO));
        return "kullanıcı eklendi";
    }

    public List<ProductSalesDTO> getAllProductSales() {
        List<ProductSales> productlist = productSalesrepository.findAll();
        if (productlist==null) {
            throw new SystemException("OrderList not found");
        }
        return ProductSalesMapper.INSTANCE.toDTOList(productlist);


    }
}
