package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTableDTO {
    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
    private int tableAmount;


}
