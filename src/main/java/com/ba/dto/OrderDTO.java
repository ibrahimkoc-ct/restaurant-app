package com.ba.dto;

import com.ba.entity.Customer;
import com.ba.entity.OrderItem;
import com.ba.entity.PaymentType;
import com.ba.entity.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private int TotalAmount;
    private int TotalCount;
    private Date date;
    private PaymentTypeDTO type;
    private WaiterDTO waiter;
    private CustomerDTO customer;

}
