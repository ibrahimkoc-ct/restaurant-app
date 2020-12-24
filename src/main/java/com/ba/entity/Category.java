package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql=
        "UPDATE CATEGORY "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
public class Category extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String imageToUrl;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TBL_CATEGORY_PRODUCT",joinColumns = @JoinColumn(name ="category_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="media_id")
    private Media media;



}