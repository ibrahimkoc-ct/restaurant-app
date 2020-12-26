package com.ba.service;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CategoryTableMapper;
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
        List<CategoryTable> categoryTableList = repository.findAll();
        if(categoryTableList.isEmpty()){
            throw new SystemException("CategoryTable Not Found");
        }
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

    public CategoryTableDTO updateCategory(CategoryTableDTO category) {
        Optional<CategoryTable> categoryTableOptional = repository.findById(category.getId());
        if (categoryTableOptional.isEmpty()) {
            throw new SystemException("Category not found in database");
        }
        UpdateHelper.updateCategoryTableHelper(category, categoryTableOptional);
        repository.saveAndFlush(categoryTableOptional.get());
        return CategoryTableMapper.INSTANCE.toDTO(categoryTableOptional.get());
    }


    public CategoryTableDTO getCategoryById(Long id) {
        Optional<CategoryTable> categoryTable = repository.findById(id);
        if (categoryTable.isEmpty()) {
            throw new SystemException("Category not found in database");
        }
        return CategoryTableMapper.INSTANCE.toDTO(categoryTable.get());

    }

}
