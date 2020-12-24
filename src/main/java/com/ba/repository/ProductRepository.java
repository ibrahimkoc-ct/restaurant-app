package com.ba.repository;

import com.ba.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @Override
    Page<Product> findAll(Pageable pageable);


    Slice<Product> findProductByCategoriesId(Long id,Pageable pageable);
}

