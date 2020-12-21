package com.ba.mapper;

import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSalesMapper {
    ProductSalesMapper INSTANCE = Mappers.getMapper(ProductSalesMapper.class);
    ProductSalesDTO toDTO(ProductSales productSales);

    ProductSales toEntity(ProductSalesDTO dto);

    List<ProductSalesDTO> toDTOList(List<ProductSales> list);

    List<ProductSales> toEntityList(List<ProductSalesDTO> dtoList);


}
