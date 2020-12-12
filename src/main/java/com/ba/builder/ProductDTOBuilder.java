package com.ba.builder;

import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import java.util.List;


public class ProductDTOBuilder extends IdBuilder{
    private String title;
    private String description;
    private String price;
    private String category;
    private String urlToImage;
    private List<CategoryDTO> categories;
    private MediaDTO mediaDTO;

    public ProductDTOBuilder categories(List<CategoryDTO> categories) {
        this.categories = categories;
        return this;
    }
    public ProductDTOBuilder mediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
        return this;
    }

    public ProductDTOBuilder description(String description) {
        this.description = description;
        return this;
    }
    public ProductDTOBuilder title(String title) {
        this.title = title;
        return this;
    }
    public ProductDTOBuilder price(String price) {
        this.price = price;
        return this;
    }
    public ProductDTOBuilder category(String category) {
        this.category = category;
        return this;
    }
    public ProductDTOBuilder urlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }
    public ProductDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }



    @Override
    public ProductDTO build(){
        ProductDTO productDTO= new ProductDTO();
        productDTO.setTitle(this.title);
        productDTO.setDescription(this.description);
        productDTO.setId(this.getId());
        productDTO.setPrice(this.price);
        productDTO.setCategory(this.category);
        productDTO.setUrlToImage(this.urlToImage);
        productDTO.setCategories(this.categories);
        productDTO.setMediaDTO(this.mediaDTO);

        return productDTO;
    }

}

