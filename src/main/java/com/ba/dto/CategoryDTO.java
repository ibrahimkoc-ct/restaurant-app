package com.ba.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<ProductDTO> products;
    private MediaDTO mediaDTO;

}
