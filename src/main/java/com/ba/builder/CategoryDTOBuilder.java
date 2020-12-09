package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.entity.Category;
import com.ba.entity.Product;

import java.util.Set;

public class CategoryDTOBuilder extends IdBuilder  {
    private String name;
    private String description;
    private String imageToUrl;
    private Set<Product> products;

    public CategoryDTOBuilder name(String name) {
        this.name = name;
        return this;

    }

    public CategoryDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public CategoryDTOBuilder description(String description) {
        this.description = description;
        return this;
    }
    public CategoryDTOBuilder imageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
        return this;
    }
    public CategoryDTOBuilder products(Set<Product> products) {
        this.products = products;
        return this;
    }
    @Override
    public CategoryDTO build(){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setProducts(this.products);
        categoryDTO.setDescription(this.description);
        categoryDTO.setImageToUrl(this.imageToUrl);
        categoryDTO.setName(this.name);
        categoryDTO.setId(this.getId());
        return categoryDTO;

    }
}
