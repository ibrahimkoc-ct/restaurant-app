package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductSales")
public class ProductSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderId;
    private Long id;
    private Long price;
    private Long piece;
    private String title;
    private String selectedtable;
    private String waiterName;
    @Column
    private Date createDate = new Timestamp(System.currentTimeMillis());


}