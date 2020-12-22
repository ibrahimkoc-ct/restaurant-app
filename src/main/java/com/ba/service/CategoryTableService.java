package com.ba.service;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import com.ba.mapper.CategoryTableMapper;
import com.ba.repository.CategoryTableRepository;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTableService {
    @Autowired
    CategoryTableRepository repository;

    public List<CategoryTableDTO> getAllCategory() {
       List<CategoryTable> categoryTableList= repository.findAll();
       return CategoryTableMapper.INSTANCE.toDTOList(categoryTableList);

    }
    public String deleteCategory(Long id) {
        repository.deleteById(id);
        return "kisi silindi";

    }
    public String addCategory(CategoryTableDTO category) {
        repository.save(CategoryTableMapper.INSTANCE.toEntity(category));
        return "kisi eklendi";
    }
    public CategoryTableDTO updateCategory (CategoryTableDTO category){
        repository.saveAndFlush(CategoryTableMapper.INSTANCE.toEntity(category));
        return category;
    }

    public CategoryTableDTO getCategoryById(Long id) {
        CategoryTable categoryTable =repository.findById(id).get();
        return CategoryTableMapper.INSTANCE.toDTO(categoryTable);

    }

}
