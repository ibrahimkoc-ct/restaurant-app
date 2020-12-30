package com.ba.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Long id;
    @NotNull(message = "Category name cannot null")
    private String name;
    @NotNull(message = "Category description cannot null")
    private String description;
    private List<ProductDTO> products;
    @NotNull(message = "Category media cannot null")
    private MediaDTO mediaDTO;

}
