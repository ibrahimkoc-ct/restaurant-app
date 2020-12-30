package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaiterDTO {
    private Long id;
    @NotNull(message = "Waiter name cannot null")
    private String name;
    @NotNull(message = "Waiter phone number cannot null")
    private String phoneNumber;
    @NotNull(message = "Waiter email cannot null")
    private String mail;
    @NotNull(message = "Waiter address cannot null")
    private String address;
    @NotNull(message = "Waiter salary cannot null")
    private Long salary;
    @NotNull(message = "Waiter media cannot null")
    private MediaDTO mediaDTO;

}
