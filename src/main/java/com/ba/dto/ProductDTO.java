package com.ba.dto;

import com.ba.entity.Category;
import com.ba.entity.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTO {
    private long id;
    private String title;
    private String description;
    private String price;
    private String category;

    private String urlToImage;
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories= new ArrayList<>();

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public ProductDTO setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }

    public ProductDTO setMediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
        return this;
    }
}
