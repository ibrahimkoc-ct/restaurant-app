package com.ba.service;

import com.ba.converter.CategoryDtoConventer;
import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.repository.CategoryRepository;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MediaRepository mediaRepository;

    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList= categoryRepository.findAll();
        return CategoryDtoConventer.convertListToDTOList(categoryList);

    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(CategoryDtoConventer.categoryDTOdeleteToCategory(id));
        return "kisi silindi";
    }

    public String addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryDtoConventer.categoryDTOaddCategory(categoryDTO));
        return "kisi eklendi";
    }

    public CategoryDTO updateCategory (CategoryDTO categoryDTO){

        Optional<Category> category = categoryRepository.findById(categoryDTO.getId());


        categoryRepository.saveAndFlush(CategoryDtoConventer.categoryDTOUpdateCategory(categoryDTO,category));
        return categoryDTO;
    }
    public CategoryDTO getCategoryById(Long id) {

        Optional<Category> dtoList =categoryRepository.findById(id);
       return CategoryDtoConventer.categoryDTOgetByID(dtoList);

    }
    public Set<ProductDTO> getProductCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return CategoryDtoConventer.categoryDTOgetProductCategory(category);

    }


}
