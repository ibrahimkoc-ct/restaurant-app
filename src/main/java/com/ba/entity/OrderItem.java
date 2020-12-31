package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "OrderItem")
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@SQLDelete(sql =
        "UPDATE OrderItem " +
                "SET deleted =true " +
                "Where id=?")
@Where(clause = "deleted =false")
public class OrderItem extends BaseEntity implements Serializable {
    private int piece;
    private int TotalPrice;
    private String selectedtable;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
