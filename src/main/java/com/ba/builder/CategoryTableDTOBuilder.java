package com.ba.builder;

import com.ba.dto.CategoryTableDTO;
import com.ba.dto.MediaDTO;


public class CategoryTableDTOBuilder extends IdBuilder {
    private String name;
    private String description;
    private int tableAmount;
    private MediaDTO mediaDTO;


    public CategoryTableDTOBuilder mediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
        return this;

    }
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

    public CategoryTableDTOBuilder tableAmount(int tableAmount){
        this.tableAmount=tableAmount;
        return this;

    }
    @Override
    public CategoryTableDTO build(){
        CategoryTableDTO categoryTableDTO = new CategoryTableDTO();
        categoryTableDTO.setDescription(this.description);
        categoryTableDTO.setTableAmount(this.tableAmount);
        categoryTableDTO.setId(this.getId());
        categoryTableDTO.setName(this.name);
        categoryTableDTO.setMediaDTO(mediaDTO);
        return categoryTableDTO;
    }
}
