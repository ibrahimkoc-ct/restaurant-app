package com.ba.entity;


import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="CategoryTable")
public class CategoryTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
    @OneToMany(
            cascade =CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Set<RestaurantTable> restaurantTables;

    public CategoryTable(String name, String description, String imageToUrl) {
        this.name = name;
        this.description = description;
        this.imageToUrl = imageToUrl;
    }

    public CategoryTable() {

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

    public Set<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(Set<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }
}
