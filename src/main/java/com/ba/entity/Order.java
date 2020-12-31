package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "OrderTable")
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@SQLDelete(sql=
        "UPDATE OrderTable "+
                "SET deleted =true "+
                "Where id=?")
@Where(clause = "deleted =false")
public class Order extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Waiter waiter;
    @ManyToOne
    @JoinColumn(name = "paymentType_id")
    private PaymentType type;
    private int TotalAmount;
    private int TotalCount;
    @Column
    private Date date = new Timestamp(System.currentTimeMillis());



}
