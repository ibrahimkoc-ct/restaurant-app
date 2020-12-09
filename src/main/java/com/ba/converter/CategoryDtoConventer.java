package com.ba.converter;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;

import java.util.*;

public class CategoryDtoConventer {
    public static List<CategoryDTO> categoryDTOListToCategory(List<Category> categoryList){
        List<CategoryDTO> categoryDTO = new ArrayList<>();
        for(Category category:categoryList){
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setDescription(category.getDescription());
            dto.setImageToUrl(category.getImageToUrl());
            dto.setProducts(category.getProducts());
            dto.setName(category.getName());
            categoryDTO.add(dto);
        }
        return categoryDTO;
    }

    public static Long categoryDTOdeleteToCategory(Long id){
        Category category= new Category();
        category.setId(id);
        return category.getId();
    }
    public static Category categoryDTOaddCategory(CategoryDTO categoryDTO){
        Category category= new Category();
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        category.setImageToUrl(categoryDTO.getImageToUrl());
        category.setName(categoryDTO.getName());
        category.setProducts(categoryDTO.getProducts());
        return category;
    }
    public static CategoryDTO categoryDTOgetByID(Optional<Category> dtoList){
        CategoryDTO dto = new CategoryDTO();
        dto.setName(dtoList.get().getName());
        dto.setProducts(dtoList.get().getProducts());
        dto.setImageToUrl(dtoList.get().getImageToUrl());
        dto.setId(dtoList.get().getId());
        dto.setProducts(dtoList.get().getProducts());

        return dto;
    }
    public static Set<ProductDTO> categoryDTOgetProductCategory(Optional<Category> category){

        Set<ProductDTO> dto = new HashSet<>();
        for(Product prod: category.get().getProducts() ){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(prod.getId());
            productDTO.setCategory(prod.getCategory());
            productDTO.setDescription(prod.getDescription());
            productDTO.setUrlToImage(prod.getUrlToImage());
            productDTO.setTitle(prod.getTitle());
            productDTO.setPrice(prod.getPrice());
            dto.add(productDTO);

        }

        return dto;

    }

}
