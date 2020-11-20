package com.ba.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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
    @Column
    private Date createDate = new Timestamp(System.currentTimeMillis());


    public ProductSales() {

    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPiece() {
        return piece;
    }

    public void setPiece(Long piece) {
        this.piece = piece;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ProductSales{" +
                "OrderId=" + OrderId +
                ", id=" + id +
                ", price=" + price +
                ", piece=" + piece +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}