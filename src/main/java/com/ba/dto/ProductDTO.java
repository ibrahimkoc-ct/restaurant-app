package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    @NotNull(message = "Product title cannot null")
    private String title;
    @NotNull(message = "Product description cannot null")
    private String description;
    @NotNull(message = "Product price cannot null")
    private String price;
    @NotNull(message = "Product media cannot null")
    private MediaDTO mediaDTO;
    private List<CategoryDTO> categories= new ArrayList<>();


}
