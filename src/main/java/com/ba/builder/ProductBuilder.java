package com.ba.builder;

import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import java.util.List;


public class ProductBuilder extends IdBuilder{
    private String title;
    private String description;
    private String price;
    private List<Category> categories;
    private Media media;

    public ProductBuilder categories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public ProductBuilder media(Media media) {
        this.media = media;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }
    public ProductBuilder title(String title) {
        this.title = title;
        return this;
    }
    public ProductBuilder price(String price) {
        this.price = price;
        return this;
    }

    public ProductBuilder id(Long id){
        this.setId(id);
        return this;
    }



    @Override
    public Product build(){
        Product product= new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setId(this.getId());
        product.setPrice(this.price);
        product.setCategories(this.categories);
        product.setMedia(this.media);
        return product;
    }

}
