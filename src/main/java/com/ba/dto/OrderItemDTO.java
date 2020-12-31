package com.ba.dto;

import com.ba.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long piece;
    private Long TotalPrice;
    private String selectedtable;
    private ProductDTO product;
    private OrderDTO order;

}
