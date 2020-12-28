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
        "UPDATE Category_Table "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
@Table(name = "Category_Table")
public class CategoryTable extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private int tableAmount;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

}
