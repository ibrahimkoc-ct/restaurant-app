package com.ba.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql=
        "UPDATE ROLES "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
public class Role extends BaseEntity implements Serializable {
    private String name;

}
