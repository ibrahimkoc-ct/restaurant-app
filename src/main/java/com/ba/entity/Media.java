package com.ba.entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql=
        "UPDATE Media "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
public class Media {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean deleted;

    @Column(length = 1000000)
    private byte[] fileContent;


}
