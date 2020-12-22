package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql=
        "UPDATE Product "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
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
    private boolean deleted;


}
