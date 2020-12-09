package com.ba.converter;

import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BackofficeDtoConverter {


    public static List<ProductDTO> productListTOProductList(List<Product> productList){
        List<ProductDTO> productDTO = new ArrayList<>();
        for(Product product:productList){
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setUrlToImage(product.getUrlToImage());
            dto.setTitle(product.getTitle());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDescription());
            dto.setCategory(product.getCategory());
            dto.setCategories(product.getCategories());
            productDTO.add(dto);
        }
        return productDTO;
    }
    public static Long deleteProductDTOToProduct(Long id){
        Product product = new Product();
        product.setId(id);
        return product.getId();
    }
    public static ProductDTO productDTOgetbyID(Optional<Product> dtoList){
        ProductDTO dto =new ProductDTO();
        dto.setId(dtoList.get().getId());
        dto.setCategories(dtoList.get().getCategories());
        dto.setCategory(dtoList.get().getCategory());
        dto.setDescription(dtoList.get().getDescription());
        dto.setUrlToImage(dtoList.get().getUrlToImage());
        dto.setTitle(dtoList.get().getTitle());
        dto.setPrice(dtoList.get().getPrice());

        return  dto;
    }
    public static Product updateProductDto(ProductDTO product){
        Product product1= new Product();
        product1.setId(product.getId());
        product1.setCategory(product.getCategory());
        product1.setUrlToImage(product.getUrlToImage());
        product1.setPrice(product.getPrice());
        product1.setCategories(product.getCategories());
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        return product1;
    }
    public static Product addProductIDtoDto(Category categorySet, ProductDTO product){

        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setDescription(product.getDescription());
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setUrlToImage(product.getUrlToImage());
        product1.setCategory(product.getCategory());
        product1.setCategories(Stream.of(categorySet).collect(Collectors.toSet()));
        categorySet.getProducts().add(product1);

       return product1;
    }
}
