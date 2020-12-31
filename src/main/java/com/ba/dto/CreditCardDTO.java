package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {
    private Long id;
    private Long number;
    private String name;
    private Long cvc;
    private Long expiry;
}
