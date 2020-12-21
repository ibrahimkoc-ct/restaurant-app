package com.ba.mapper;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryTableMapper {

    CategoryTableMapper INSTANCE = Mappers.getMapper(CategoryTableMapper.class);
    CategoryTableDTO toDTO(CategoryTable categoryTable);
    CategoryTable toEntity(CategoryTableDTO dto);
    List<CategoryTable> toEntityList(List<CategoryTableDTO> dtoList);
    List<CategoryTableDTO> toDTOList(List<CategoryTable> categoryTableList);
}
