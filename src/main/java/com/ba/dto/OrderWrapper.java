package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderWrapper {
    private Long productId;
    private String title;
    private int price;
    private int piece;
    private Long total;
    private String selectedtable;
    private Long waiterId;
    private Long customerId;


}
