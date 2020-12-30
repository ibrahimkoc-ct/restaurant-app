package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesDTO {

    private Long id;
    @NotNull(message = "Order productId cannot null")
    private Long productId;
    @NotNull(message = "Order price cannot null")
    private Long price;
    @NotNull(message = "Order piece cannot null")
    private Long piece;
    @NotNull(message = "Order title cannot null")
    private String title;
    @NotNull(message = "Order selected Table cannot null")
    private String selectedtable;
    private Date createDate = new Timestamp(System.currentTimeMillis());
    @NotNull(message = "Order waiter Name cannot null")
    private String waiterName;


}