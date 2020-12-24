package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql=
        "UPDATE CUSTOMER "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
@Entity
public class Customer extends BaseEntity implements Serializable {

    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

}
