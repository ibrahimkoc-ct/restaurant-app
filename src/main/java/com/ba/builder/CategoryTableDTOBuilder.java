package com.ba.builder;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;

public class CategoryTableDTOBuilder extends IdBuilder {
    private String name;
    private String description;
    private String imageToUrl;
    private int tableAmount;
    public CategoryTableDTOBuilder name(String name) {
        this.name = name;
        return this;

    }
    public CategoryTableDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public CategoryTableDTOBuilder description(String description) {
        this.description = description;
        return this;
    }
    public CategoryTableDTOBuilder imageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
        return this;
    }
    public CategoryTableDTOBuilder tableAmount(int tableAmount){
        this.tableAmount=tableAmount;
        return this;

    }
    @Override
    public CategoryTableDTO build(){
        CategoryTableDTO categoryTableDTO = new CategoryTableDTO();
        categoryTableDTO.setImageToUrl(this.imageToUrl);
        categoryTableDTO.setTableAmount(this.tableAmount);
        categoryTableDTO.setId(this.getId());
        categoryTableDTO.setName(this.name);
        return categoryTableDTO;
    }
}
