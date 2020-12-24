package com.ba.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProductWrapperDTO {
    List<ProductDTO> listProductDto;
    Long pageSize;


}
