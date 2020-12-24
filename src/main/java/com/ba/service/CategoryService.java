package com.ba.service;

import com.ba.dto.CategoryDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.MediaMapper;
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


    @Cacheable(value = "CategoryCache",key="'CATEGORY_LIST_ALL'")
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList= categoryRepository.findAll();
        return CategoryMapper.INSTANCE.toDTOList(categoryList);
    }
    @CacheEvict(value = "CategoryCache",allEntries = true)
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "kisi silindi";
    }
    @CacheEvict(value = "CategoryCache",allEntries = true)
    public String addCategory(CategoryDTO categoryDTO) {
        Category category=CategoryMapper.INSTANCE.toEntity(categoryDTO);
        category.setMedia(MediaMapper.INSTANCE.toEntity(categoryDTO.getMediaDTO()));
        category.setProducts(ProductMapper.INSTANCE.toEntity(categoryDTO.getProducts()));
       categoryRepository.save(category);
        return "kisi eklendi";
    }
    @CacheEvict(value = "CategoryCache",allEntries = true)
    public CategoryDTO updateCategory (CategoryDTO categoryDTO){
        Optional<Category> categoryList = categoryRepository.findById(categoryDTO.getId());
        Category category=CategoryMapper.INSTANCE.toEntity(categoryDTO);
        category.setMedia(MediaMapper.INSTANCE.toEntity(categoryDTO.getMediaDTO()));
        category.setProducts(categoryList.get().getProducts());
        categoryRepository.saveAndFlush(category);

        return categoryDTO;
    }
    @Cacheable(value = "CATEGORY_CACHE_BY",key = "'CUSTOMER_CACHE_NY_ID'.concat(#id)")
    public CategoryDTO getCategoryById(Long id) {
        Category dto =categoryRepository.findById(id).get();
        return CategoryMapper.INSTANCE.toDTO(dto);

    }
    @Cacheable(value = "CATEGORY_CACHE_BY",key = "'CUSTOMER_CACHE_NY_ID'.concat(#id)")
    public List<ProductDTO> getProductCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        List<ProductDTO> dtoList=ProductMapper.INSTANCE.toDTOList(category.get().getProducts());
        return dtoList;
    }
}
