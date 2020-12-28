package com.ba.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTableDTO {
    private Long id;
    private String name;
    private String description;
    private int tableAmount;
    private MediaDTO mediaDTO;


}
