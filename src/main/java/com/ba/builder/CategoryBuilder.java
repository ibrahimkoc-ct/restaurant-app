package com.ba.builder;

import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;

import java.util.List;

public class CategoryBuilder extends IdBuilder {
    private String name;
    private String description;
    private String imageToUrl;
    private List<Product> products;
    private Media media;

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;

    }
    public CategoryBuilder media(Media media) {
        this.media = media;
        return this;

    }

    public CategoryBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public CategoryBuilder description(String description) {
        this.description = description;
        return this;
    }
    public CategoryBuilder imageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
        return this;
    }
    public CategoryBuilder products(List<Product> products) {
        this.products = products;
        return this;
    }
    @Override
    public Category build(){
        Category category = new Category();
        category.setProducts(this.products);
        category.setDescription(this.description);
        category.setImageToUrl(this.imageToUrl);
        category.setName(this.name);
        category.setId(this.getId());
        category.setMedia(this.media);
        return category;

    }

}
