package com.ba.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
    @OneToMany(
            cascade =CascadeType.ALL)
    @JoinColumn(name = "category_id")
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
