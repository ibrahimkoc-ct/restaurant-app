package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity

public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "TBL_CATEGORY_PRODUCT",joinColumns = @JoinColumn(name ="category_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))

    private Set<Product> products;

    public Category(String name, String description, String imageToUrl) {
        this.name = name;
        this.description = description;
        this.imageToUrl = imageToUrl;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageToUrl() {
        return imageToUrl;
    }

    public void setImageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageToUrl='" + imageToUrl + '\'' +
                ", products=" + products +
                '}';
    }
}
