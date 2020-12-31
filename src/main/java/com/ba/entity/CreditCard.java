package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "CreditCard")
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql=
        "UPDATE CreditCard "+
                "SET deleted =true "+
                "Where id=?")
@Where(clause = "deleted =false")

public class CreditCard extends BaseEntity implements Serializable {
    private Long number;
    private String name;
    private Long cvc;
    private Long expiry;
}
