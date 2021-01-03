package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotNull(message = "Customer name cannot null")
    private String name;
    @NotNull(message = "Customer name cannot null")
    private String surname;
    @NotNull(message = "Customer surname cannot null")
    private String phoneNumber;
    @NotNull(message = "Customer phoneNumber cannot null")
    private String address;
}
