package com.ba.builder;

import com.ba.entity.ProductSales;
import java.util.Date;

public class ProductSalesBuilder extends IdBuilder {
    private Long OrderId;
    private Long price;
    private Long piece;
    private String title;
    private String selectedtable;
    private String waiterName;
    private Date createDate;

    public ProductSalesBuilder title(String title) {
        this.title = title;
        return this;
    }
    public ProductSalesBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
    public ProductSalesBuilder price(Long price) {
        this.price = price;
        return this;
    }
    public ProductSalesBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public ProductSalesBuilder piece(Long piece) {
        this.piece = piece;
        return this;
    }
    public ProductSalesBuilder OrderId(Long OrderId) {
        this.OrderId = OrderId;
        return this;
    }
    public ProductSalesBuilder selectedtable(String selectedtable) {
        this.selectedtable = selectedtable;
        return this;
    }
    public ProductSalesBuilder waiterName(String waiterName) {
        this.waiterName = waiterName;
        return this;
    }
    @Override
    public ProductSales build(){
        ProductSales productSales= new ProductSales();
        productSales.setWaiterName(this.waiterName);
        productSales.setPiece(this.piece);
        productSales.setPrice(this.price);
        productSales.setOrderId(this.OrderId);
        productSales.setId(this.getId());
        productSales.setCreateDate(this.createDate);
        productSales.setTitle(this.title);
        productSales.setSelectedtable(this.selectedtable);
        return productSales;
    }


}
