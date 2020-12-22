package com.ba.dto;

import java.util.List;

public class ProductWrapperDTO {
    List<ProductDTO> listProductDto;
    Long pageSize;

    public List<ProductDTO> getListProductDto() {
        return listProductDto;
    }

    public ProductWrapperDTO setListProductDto(List<ProductDTO> listProductDto) {
        this.listProductDto = listProductDto;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public ProductWrapperDTO setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
