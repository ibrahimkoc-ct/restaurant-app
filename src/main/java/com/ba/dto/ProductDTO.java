package com.ba.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String price;
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories= new ArrayList<>();


}
