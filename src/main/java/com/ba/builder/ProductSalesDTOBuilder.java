package com.ba.builder;

import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;

import java.util.Date;

public class ProductSalesDTOBuilder  extends IdBuilder {
    private Long OrderId;
    private Long price;
    private Long piece;
    private String title;
    private String selectedtable;
    private String waiterName;
    private Date createDate;

    public ProductSalesDTOBuilder title(String title) {
        this.title = title;
        return this;
    }
    public ProductSalesDTOBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
    public ProductSalesDTOBuilder price(Long price) {
        this.price = price;
        return this;
    }
    public ProductSalesDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public ProductSalesDTOBuilder piece(Long piece) {
        this.piece = piece;
        return this;
    }
    public ProductSalesDTOBuilder OrderId(Long OrderId) {
        this.OrderId = OrderId;
        return this;
    }
    public ProductSalesDTOBuilder selectedtable(String selectedtable) {
        this.selectedtable = selectedtable;
        return this;
    }
    public ProductSalesDTOBuilder waiterName(String waiterName) {
        this.waiterName = waiterName;
        return this;
    }
    @Override
    public ProductSalesDTO build(){
        ProductSalesDTO productSalesDTO= new ProductSalesDTO();
        productSalesDTO.setWaiterName(this.waiterName);
        productSalesDTO.setPiece(this.piece);
        productSalesDTO.setPrice(this.price);
        productSalesDTO.setOrderId(this.OrderId);
        productSalesDTO.setId(this.getId());
        productSalesDTO.setCreateDate(this.createDate);
        productSalesDTO.setTitle(this.title);
        productSalesDTO.setSelectedtable(this.selectedtable);
        return productSalesDTO;
    }


}