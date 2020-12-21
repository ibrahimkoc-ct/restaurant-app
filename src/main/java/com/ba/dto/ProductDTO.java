package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long id;
    private String title;
    private String description;
    private String price;
    private String category;
    private String urlToImage;
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories= new ArrayList<>();


}
