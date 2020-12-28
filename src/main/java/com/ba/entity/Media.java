package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql =
        "UPDATE Media " +
                "SET deleted =true " +
                "Where id=?")
@Where(clause = "deleted =false")
@Table(name = "Media")
public class Media extends BaseEntity implements Serializable {


    private String name;


    @Column(length = 1000000)
    private byte[] fileContent;


}
