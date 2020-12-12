package com.ba.service;

import com.ba.converter.CategoryTableDtoConverter;
import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import com.ba.repository.CategoryTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryTableService {
    @Autowired
    CategoryTableRepository repository;

    public List<CategoryTableDTO> getAllCategory() {
       List<CategoryTable> categoryTableList= repository.findAll();
        return CategoryTableDtoConverter.categoryListToCategoryDTOList(categoryTableList);

    }
    public String deleteCategory(Long id) {
        repository.deleteById(CategoryTableDtoConverter.categoryTabledelete(id));
        return "kisi silindi";

    }
    public String addCategory(CategoryTableDTO category) {
        repository.save(CategoryTableDtoConverter.categoryTableAddDTO(category));
        return "kisi eklendi";
    }
    public CategoryTableDTO updateCategory (CategoryTableDTO category){
        repository.saveAndFlush(CategoryTableDtoConverter.categoryTableAddDTO(category));
        return category;
    }

    public CategoryTableDTO getCategoryById(Long id) {
        Optional<CategoryTable> dtoList= repository.findById(id);
       return CategoryTableDtoConverter.categoryTableDTOgetById(dtoList);

    }

}
