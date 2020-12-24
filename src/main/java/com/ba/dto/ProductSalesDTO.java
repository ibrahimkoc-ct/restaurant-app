package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesDTO {
    private Long productId;
    private Long id;
    private Long price;
    private Long piece;
    private String title;
    private String selectedtable;
    private Date createDate = new Timestamp(System.currentTimeMillis());
    private String waiterName;


}