package com.ba.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql=
        "UPDATE Roles "+
                "SET deleted =true "+
                "Where id=?")
@Where( clause = "deleted =false")
public class Role extends BaseEntity implements Serializable {
    private String name;

}
