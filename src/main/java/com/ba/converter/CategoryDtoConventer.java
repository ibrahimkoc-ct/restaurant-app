package com.ba.converter;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;
import java.util.*;
public class CategoryDtoConventer {

    public static List<Category> convertDTOListToList(List<CategoryDTO> categoryDTOList){
        List<Category> list = new ArrayList<>();
        for(CategoryDTO dto: categoryDTOList){
            Category category = new Category();
            category.setId(dto.getId());
            category.setName(dto.getName());
            category.setDescription(dto.getDescription());
            category.setImageToUrl(dto.getImageToUrl());
            category.setProducts(BackofficeDtoConverter.convertDTOListToList(dto.getProducts()));
            list.add(category);
        }
        return list;
    }
    public static List<CategoryDTO> convertListToDTOList(List<Category> categoryList){
        List<CategoryDTO> dtoList = new ArrayList<>();

        for(Category category: categoryList){
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());
            dto.setImageToUrl(category.getImageToUrl());
            dto.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(category.getMedia()));
            dtoList.add(dto);
        }
        return dtoList;
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
        category.setProducts(BackofficeDtoConverter.convertDTOListToList(categoryDTO.getProducts()));
        category.setMedia(MediaDtoConventer.mediaDTOtoMedia(categoryDTO.getMediaDTO()));
        return category;
    }
    public static Category categoryDTOUpdateCategory(CategoryDTO categoryDTO,Optional<Category> category2 ){
        Category category= new Category();
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        category.setImageToUrl(categoryDTO.getImageToUrl());
        category.setName(categoryDTO.getName());
        category.setMedia(MediaDtoConventer.mediaDTOtoMedia(categoryDTO.getMediaDTO()));
        category.setProducts(category2.get().getProducts());
        return category;
    }
    public static CategoryDTO categoryDTOgetByID(Optional<Category> dtoList){
        CategoryDTO dto = new CategoryDTO();
        dto.setName(dtoList.get().getName());
        dto.setProducts(BackofficeDtoConverter.convertListtoDTOList(dtoList.get().getProducts()));
        dto.setImageToUrl(dtoList.get().getImageToUrl());
        dto.setId(dtoList.get().getId());
        dto.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(dtoList.get().getMedia()));
      dto.setDescription(dtoList.get().getDescription());
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
            productDTO.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(prod.getMedia()));
            dto.add(productDTO);
        }
        return dto;
    }
}
