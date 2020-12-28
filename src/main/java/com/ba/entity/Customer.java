package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql=
        "UPDATE Customer "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
@Entity
@Table(name = "Customer")
public class Customer extends BaseEntity implements Serializable {

    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
}
