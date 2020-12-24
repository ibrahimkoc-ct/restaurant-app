package com.ba.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql=
        "UPDATE CATEGORY_TABLE "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
public class CategoryTable extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String imageToUrl;
    private int tableAmount;

}
