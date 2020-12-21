package com.ba.mapper;

import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source ="media", target = "mediaDTO")
    @Mapping(target ="categories", ignore = true)
    ProductDTO toDTO(Product product);

    @Mapping(target ="categories", ignore = true)
    @Mapping(source = "mediaDTO",target = "media")
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDTOList(List<Product> productList);
    List<Product> toEntity(List<ProductDTO> productDTOList);

}
