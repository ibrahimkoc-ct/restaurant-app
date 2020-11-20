package com.ba.repository;

import com.ba.entity.Product;
import com.ba.entity.ProductSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductSalesRepository extends JpaRepository<ProductSales, Long> {



}
