package com.ba.mapper;

import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-24T22:32:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class CategoryTableMapperImpl implements CategoryTableMapper {

    @Override
    public CategoryTableDTO toDTO(CategoryTable categoryTable) {
        if ( categoryTable == null ) {
            return null;
        }

        CategoryTableDTO categoryTableDTO = new CategoryTableDTO();

        categoryTableDTO.setId( categoryTable.getId() );
        categoryTableDTO.setName( categoryTable.getName() );
        categoryTableDTO.setDescription( categoryTable.getDescription() );
        categoryTableDTO.setImageToUrl( categoryTable.getImageToUrl() );
        categoryTableDTO.setTableAmount( categoryTable.getTableAmount() );

        return categoryTableDTO;
    }

    @Override
    public CategoryTable toEntity(CategoryTableDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryTable categoryTable = new CategoryTable();

        categoryTable.setId( dto.getId() );
        categoryTable.setName( dto.getName() );
        categoryTable.setDescription( dto.getDescription() );
        categoryTable.setImageToUrl( dto.getImageToUrl() );
        categoryTable.setTableAmount( dto.getTableAmount() );

        return categoryTable;
    }

    @Override
    public List<CategoryTable> toEntityList(List<CategoryTableDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CategoryTable> list = new ArrayList<CategoryTable>( dtoList.size() );
        for ( CategoryTableDTO categoryTableDTO : dtoList ) {
            list.add( toEntity( categoryTableDTO ) );
        }

        return list;
    }

    @Override
    public List<CategoryTableDTO> toDTOList(List<CategoryTable> categoryTableList) {
        if ( categoryTableList == null ) {
            return null;
        }

        List<CategoryTableDTO> list = new ArrayList<CategoryTableDTO>( categoryTableList.size() );
        for ( CategoryTable categoryTable : categoryTableList ) {
            list.add( toDTO( categoryTable ) );
        }

        return list;
    }
}
