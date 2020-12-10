package com.ba.service;

import com.ba.converter.BackofficeDtoConverter;
import com.ba.converter.CategoryDtoConventer;
import com.ba.converter.ProductSalesDtoConverter;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.CategoryTable;
import com.ba.entity.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class BackofficeService {
    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProduct() {
        List<Product> productList=repository.findAll();
    return BackofficeDtoConverter.productListTOProductList(productList);
    }

    public String deleteProduct(Long id) {
//        Optional<Product> product2 =

        Product product=repository.findById(id).get();
        List<Category> categoryList=product.getCategories();
        for(int i=0; i<categoryList.size(); i++){
            categoryList.get(i).getProducts().remove(product);
            categoryRepository.save(categoryList.get(i));
        }
        repository.deleteById(id);






        return "kisi silindi";
    }



    public ProductDTO updateProduct(long id, ProductDTO product) {

        repository.saveAndFlush(BackofficeDtoConverter.updateProductDto(product));
        return product;
    }

    public ProductDTO getProductById(Long id) {

        Optional<Product> dtoList =repository.findById(id);
        return BackofficeDtoConverter.productDTOgetbyID(dtoList);

    }


    public String  addProductId(ProductDTO product, Long id){
            Product product1=new Product();
            List<Category> categoryList= new ArrayList<>();
            for(int i=0; i<product.getCategories().size(); i++){
                Category category=categoryRepository.findById(product.getCategories().get(i).getId()).get();
                categoryList.add(category);
            }

        product1=BackofficeDtoConverter.addProductIDtoDto(categoryList,product);
//        Category category =categoryRepository.findById(id).get();

        repository.save(product1);

        return "kisi eklendi";
    }

}