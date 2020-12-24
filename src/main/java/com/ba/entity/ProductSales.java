package com.ba.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductSales")
@SQLDelete(sql=
        "UPDATE ProductSales "+
                "SET deleted =true "+
                "Where OrderId=?")
@Where( clause = "deleted =false")
public class ProductSales extends BaseEntity implements Serializable {

    private Long productId;
    private Long price;
    private Long piece;
    private String title;
    private String selectedtable;
    private String waiterName;
    @Column
    private Date createDate = new Timestamp(System.currentTimeMillis());


}