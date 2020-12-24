package com.ba.service;

import com.ba.dto.ProductDTO;
import com.ba.dto.ProductWrapperDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BackofficeService {
    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProduct() {
        List<Product> productList = repository.findAll();
        List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(productList);
        for (int i = 0; i < productList.size(); i++) {
            productDTOList.get(i).setCategories(CategoryMapper.INSTANCE.toDTOList(productList.get(i).getCategories()));
        }
        return productDTOList;
    }

    public String deleteProduct(Long id) {
        Product product = repository.findById(id).get();
        List<Category> categoryList = product.getCategories();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).getProducts().remove(product);
            categoryRepository.save(categoryList.get(i));
        }
        repository.deleteById(id);
        return "kisi silindi";
    }

    public ProductDTO updateProduct(long id, ProductDTO product) {
        Product product1 = repository.findById(id).get();

        List<Category> categoryList = product1.getCategories();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).getProducts().remove(product1);
            categoryRepository.save(categoryList.get(i));
        }
        List<Category> categoryList1 = new ArrayList<>();
        for (int i = 0; i < product.getCategories().size(); i++) {
            Category category = categoryRepository.findById(product.getCategories().get(i).getId()).get();
            categoryList1.add(category);
        }
        Product product2 = ProductMapper.INSTANCE.toEntity(product);
        product2.setMedia(MediaMapper.INSTANCE.toEntity(product.getMediaDTO()));
        for (int i = 0; i < categoryList1.size(); i++) {
            categoryList1.get(i).getProducts().add(product2);
            product2.setCategories(Stream.of(categoryList.get(i)).collect(Collectors.toList()));
        }
        repository.save(product2);
//        repository.save(BackofficeDtoConverter.addProductIDtoDto(categoryList1, product));
        return product;
    }

    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id).get();
        return ProductMapper.INSTANCE.toDTO(product);
    }

    public String addProductId(ProductDTO product, Long id) {

        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < product.getCategories().size(); i++) {
            Category category = categoryRepository.findById(product.getCategories().get(i).getId()).get();
            categoryList.add(category);
        }
        Product product1 = ProductMapper.INSTANCE.toEntity(product);
        product1.setMedia(MediaMapper.INSTANCE.toEntity(product.getMediaDTO()));
        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).getProducts().add(product1);
            product1.setCategories(Stream.of(categoryList.get(i)).collect(Collectors.toList()));
        }
        repository.save(product1);
        return "kisi eklendi";
    }

    public ProductWrapperDTO getPageProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> pageProductList = repository.findAll(pageable);
        List<Product> dtoList = pageProductList.toList();
        List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(dtoList);
        for (int i = 0; i < dtoList.size(); i++) {
            productDTOList.get(i).setCategories(CategoryMapper.INSTANCE.toDTOList(dtoList.get(i).getCategories()));
        }
        ProductWrapperDTO wrapperDTO = new ProductWrapperDTO();
        wrapperDTO.setListProductDto(productDTOList);
        wrapperDTO.setPageSize((long) pageable.getPageSize());

        return wrapperDTO;

    }
}