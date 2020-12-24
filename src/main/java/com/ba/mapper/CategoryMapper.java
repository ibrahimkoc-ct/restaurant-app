package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source ="media", target = "mediaDTO")
    @Mapping(target ="products", ignore = true)
    CategoryDTO toDTO(Category category);

    @Mapping(ignore = true,source = "mediaDTO",target = "media")
    @Mapping(target ="products", ignore = true)
    Category toEntity(CategoryDTO dto);


    List<CategoryDTO> toDTOList(List<Category> categoryList);

    List<Category> toEntityList (List<CategoryDTO> dtoList);

}
