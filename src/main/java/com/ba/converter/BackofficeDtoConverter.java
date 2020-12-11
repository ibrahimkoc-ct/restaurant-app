package com.ba.converter;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import liquibase.pro.packaged.C;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BackofficeDtoConverter {

    public static List<ProductDTO> convertListtoDTOList(List<Product> products){
        List<ProductDTO> dtoList = new ArrayList<>();

        for(Product product:products){
            ProductDTO dto = new ProductDTO();
            dto.setPrice(product.getPrice());
            dto.setTitle(product.getTitle());
            dto.setDescription(product.getDescription());
            dto.setCategory(product.getCategory());
            dto.setId(product.getId());
            dto.setUrlToImage(product.getUrlToImage());
            dto.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(product.getMedia()));
            dto.setCategories(CategoryDtoConventer.convertListToDTOList(product.getCategories()));

            dtoList.add(dto);
        }
        return dtoList;
    }
    public static List<Product> convertDTOListToList(List<ProductDTO> productDTOList){
        List<Product> list = new ArrayList<>();

        for(ProductDTO dto:productDTOList){
            Product product = new Product();

            product.setPrice(dto.getPrice());
            product.setCategory(dto.getCategory());
            product.setDescription(dto.getDescription());
            product.setTitle(dto.getTitle());
            product.setId(dto.getId());
            product.setUrlToImage(dto.getUrlToImage());
            product.setMedia(MediaDtoConventer.mediaDTOtoMedia(dto.getMediaDTO()));
            product.setCategories(CategoryDtoConventer.convertDTOListToList(dto.getCategories()));

            list.add(product);
        }
        return list;
    }


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
            dto.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(product.getMedia()));

//          dto.setCategories(CategoryDtoConventer.convertListToDTOList(product.getCategories()));
            productDTO.add(dto);
        }
        return productDTO;
    }
    public static Long deleteProductDTOToProduct(Long id){
        Product product1 = new Product();

        product1.setId(id);
        return product1.getId();
    }
    public static ProductDTO productDTOgetbyID(Optional<Product> dtoList){
        ProductDTO dto =new ProductDTO();
        dto.setId(dtoList.get().getId());
        dto.setCategories(CategoryDtoConventer.convertListToDTOList(dtoList.get().getCategories()));
        dto.setCategory(dtoList.get().getCategory());
        dto.setDescription(dtoList.get().getDescription());
        dto.setUrlToImage(dtoList.get().getUrlToImage());
        dto.setTitle(dtoList.get().getTitle());
        dto.setPrice(dtoList.get().getPrice());
        dto.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(dtoList.get().getMedia()));

        return  dto;
    }
    public static Product updateProductDto(ProductDTO product){
        Product product1= new Product();
        product1.setId(product.getId());
        product1.setCategory(product.getCategory());
        product1.setUrlToImage(product.getUrlToImage());
        product1.setPrice(product.getPrice());
        product1.setCategories(CategoryDtoConventer.convertDTOListToList(product.getCategories()));
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setMedia(MediaDtoConventer.mediaDTOtoMedia(product.getMediaDTO()));
        return product1;
    }
    public static Product addProductIDtoDto(List<Category> categoryList, ProductDTO product){


        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setDescription(product.getDescription());
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setUrlToImage(product.getUrlToImage());
        product1.setCategory(product.getCategory());
        product1.setMedia(MediaDtoConventer.mediaDTOtoMedia(product.getMediaDTO()));


      for(int i=0; i<categoryList.size(); i++){
         categoryList.get(i).getProducts().add(product1);
          product1.setCategories(Stream.of(categoryList.get(i)).collect(Collectors.toList()));
      }

       return product1;
    }
    public static Product convertProductDtoToProduct(Product entity ,ProductDTO dto, Optional<Category> productcategory){

        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());
        entity.setUrlToImage(dto.getUrlToImage());
        entity.setId(dto.getId());
        entity.getCategories().add(productcategory.get());
        entity.setMedia(MediaDtoConventer.mediaDTOtoMedia(dto.getMediaDTO()));
        return entity;
    }
}
