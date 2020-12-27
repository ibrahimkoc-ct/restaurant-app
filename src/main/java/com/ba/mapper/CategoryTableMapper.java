package com.ba.mapper;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MediaMapper.class})
public interface CategoryTableMapper {

    CategoryTableMapper INSTANCE = Mappers.getMapper(CategoryTableMapper.class);
    @Mapping(source ="media", target = "mediaDTO")
    CategoryTableDTO toDTO(CategoryTable categoryTable);
    @Mapping(source = "mediaDTO",target = "media")
    CategoryTable toEntity(CategoryTableDTO dto);
    List<CategoryTable> toEntityList(List<CategoryTableDTO> dtoList);
    List<CategoryTableDTO> toDTOList(List<CategoryTable> categoryTableList);
}
