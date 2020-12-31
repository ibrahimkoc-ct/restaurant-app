package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderandCreditDTO {
    private List<OrderWrapper>  orderWrapperList;
    private CreditCardDTO creditCardDTO;
    private Long paymentId;
}
