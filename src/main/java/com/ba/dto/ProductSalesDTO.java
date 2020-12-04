package com.ba.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ProductSalesDTO {
    private Long OrderId;
    private Long id;
    private Long price;
    private Long piece;
    private String title;
    private String selectedtable;
    private Date createDate = new Timestamp(System.currentTimeMillis());

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getSelectedtable() {
        return selectedtable;
    }

    public void setSelectedtable(String selectedtable) {
        this.selectedtable = selectedtable;
    }
}