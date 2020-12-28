package com.ba.service;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MediaRepository mediaRepository;


    @Cacheable(value = "CategoryCache", key = "'CATEGORY_LIST_ALL'")
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList==null) {
            throw new SystemException("Categories not found!");
        }
        return CategoryMapper.INSTANCE.toDTOList(categoryList);
    }

    @CacheEvict(value = "CategoryCache", allEntries = true)
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "kisi silindi";
    }

    @CacheEvict(value = "CategoryCache", allEntries = true)
    public String addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryMapper.INSTANCE.toEntity(categoryDTO));
        return "kisi eklendi";
    }

    @CacheEvict(value = "CategoryCache", allEntries = true)
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getId());
        if (optionalCategory==null) {
            throw new SystemException("Category Not found");
        }
        Category category = UpdateHelper.categoryUpdateHelper(optionalCategory.get(), categoryDTO);
        categoryRepository.saveAndFlush(category);
        return CategoryMapper.INSTANCE.toDTO(category);
    }

    @Cacheable(value = "CATEGORY_CACHE_BY", key = "'CUSTOMER_CACHE_NY_ID'.concat(#id)")
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> dto = categoryRepository.findById(id);
        if (dto==null) {
            throw new SystemException("Category Not found");
        }
        return CategoryMapper.INSTANCE.toDTO(dto.get());
    }
    @Cacheable(value = "CATEGORY_CACHE_BY", key = "'CUSTOMER_CACHE_NY_ID'.concat(#id)")
    public List<ProductDTO> getProductCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category==null) {
            throw new SystemException("Category Not found");
        }
        List<ProductDTO> dtoList = ProductMapper.INSTANCE.toDTOList(category.get().getProducts());
        return dtoList;
    }
}
