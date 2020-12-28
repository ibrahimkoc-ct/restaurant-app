package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql=
        "UPDATE Product "+
                "SET deleted =true "+
                "Where id=?")
@Where(clause = "deleted =false")
@Table(name = "Product")
public class Product extends BaseEntity implements Serializable {
    private String title;
    private String description;
    private String price;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();



}