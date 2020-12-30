package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTableDTO {
    private Long id;
    @NotNull(message = "Category Table name cannot null")
    private String name;
    @NotNull(message = "Category Table description cannot null")
    private String description;
    @NotNull(message = "Category Table amount cannot null")
    private int tableAmount;
    @NotNull(message = "Category Table media cannot null")
    private MediaDTO mediaDTO;


}
