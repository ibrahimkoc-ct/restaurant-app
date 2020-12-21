package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String price;
    private String category;
    private String urlToImage;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;



    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Category> categories= new ArrayList<>();


}
