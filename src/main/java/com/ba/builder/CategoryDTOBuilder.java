package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import java.util.List;


public class CategoryDTOBuilder extends IdBuilder  {
    private String name;
    private String description;
    private String imageToUrl;
    private List<ProductDTO> products;
    private MediaDTO mediaDTO;

    public CategoryDTOBuilder mediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
        return this;

    }

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
    public CategoryDTOBuilder products(List<ProductDTO> products) {
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
        categoryDTO.setMediaDTO(this.mediaDTO);
        return categoryDTO;

    }
}
