package com.ba.service;

import com.ba.entity.Product;
import com.ba.entity.ProductSales;
import com.ba.repository.ProductSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSalesService {
    @Autowired
    ProductSalesRepository productSalesrepository;

    public void addProductSales(List<ProductSales> products) {
        productSalesrepository.saveAll(products);

    }
    public List<ProductSales> getAllProductSales() {
        productSalesrepository.findAll();
        return productSalesrepository.findAll();
    }


}
