package com.ba.converter;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryTableDtoConverter {
    public static List<CategoryTableDTO> categoryListToCategoryDTOList(List<CategoryTable> categoryTableList){
        List<CategoryTableDTO> tableDTO= new ArrayList<>();
        for(CategoryTable table:categoryTableList){
            CategoryTableDTO cDto = new CategoryTableDTO();
            cDto.setId(table.getId());
            cDto.setDescription(table.getDescription());
            cDto.setImageToUrl(table.getImageToUrl());
            cDto.setName(table.getName());
            cDto.setTableAmount(table.getTableAmount());
            tableDTO.add(cDto);
        }
        return tableDTO;
    }

    public static Long categoryTabledelete(Long id){
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setId(id);
        return categoryTable.getId();
    }
    public static CategoryTable categoryTableAddDTO(CategoryTableDTO category){
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setId(category.getId());
        categoryTable.setDescription(category.getDescription());
        categoryTable.setImageToUrl(category.getImageToUrl());
        categoryTable.setTableAmount(category.getTableAmount());
        categoryTable.setName(category.getName());
        return categoryTable;
    }

    public static CategoryTableDTO categoryTableDTOgetById(Optional<CategoryTable> dtoList){
        CategoryTableDTO dto = new CategoryTableDTO();
        dto.setTableAmount(dtoList.get().getTableAmount());
        dto.setName(dtoList.get().getName());
        dto.setImageToUrl(dtoList.get().getImageToUrl());
        dto.setId(dtoList.get().getId());
        dto.setDescription(dtoList.get().getDescription());
        return dto;
    }
}
