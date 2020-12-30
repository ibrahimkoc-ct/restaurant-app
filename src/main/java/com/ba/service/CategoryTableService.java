package com.ba.service;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import com.ba.entity.Media;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CategoryTableMapper;
import com.ba.mapper.MediaMapper;
import com.ba.repository.CategoryTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryTableService {
    @Autowired
    CategoryTableRepository repository;

    public List<CategoryTableDTO> getAllCategory() {
        List<CategoryTable> categoryTableList = repository.findAll();
        if(categoryTableList==null){
            throw new SystemException("CategoryTable Not Found");
        }
        return CategoryTableMapper.INSTANCE.toDTOList(categoryTableList);
    }
    @Transactional
    public String deleteCategory(Long id) {
        repository.deleteById(id);
        return "kisi silindi";
    }
    @Transactional
    public String addCategory(CategoryTableDTO categoryTableDTO) {
        CategoryTable categoryTable =CategoryTableMapper.INSTANCE.toEntity(categoryTableDTO);
        repository.save(categoryTable);
        return "kisi eklendi";
    }
    @Transactional
    public CategoryTableDTO updateCategory(CategoryTableDTO category) {
        Optional<CategoryTable> categoryTableOptional = repository.findById(category.getId());
        if (categoryTableOptional==null) {
            throw new SystemException("Category not found in database");
        }
        UpdateHelper.updateCategoryTableHelper(category, categoryTableOptional);
        repository.saveAndFlush(categoryTableOptional.get());
        return CategoryTableMapper.INSTANCE.toDTO(categoryTableOptional.get());

    }
    public CategoryTableDTO getCategoryById(Long id) {
        Optional<CategoryTable> categoryTable = repository.findById(id);
        if (categoryTable==null) {
            throw new SystemException("Category not found in database");
        }
        return CategoryTableMapper.INSTANCE.toDTO(categoryTable.get());
    }

}
