package com.ba.repository;

import com.ba.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

@Query("Select DISTINCT category From Product")
    public List<String> getAllCategory();
@Query("Select p from Product p Where p.category=:categoryName")
    public List<Product> findCategoryByName(String categoryName);


}
