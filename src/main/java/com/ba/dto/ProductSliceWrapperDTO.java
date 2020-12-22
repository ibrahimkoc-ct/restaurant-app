package com.ba.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ProductSliceWrapperDTO {
   private List<ProductDTO> listProductDto;
    private boolean hasNext;
}
