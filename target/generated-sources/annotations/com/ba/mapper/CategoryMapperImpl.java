package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T22:51:45+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setMediaDTO( mediaToMediaDTO( category.getMedia() ) );
        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setImageToUrl( category.getImageToUrl() );

        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( dto.getId() );
        category.setName( dto.getName() );
        category.setDescription( dto.getDescription() );
        category.setImageToUrl( dto.getImageToUrl() );

        return category;
    }

    @Override
    public List<CategoryDTO> toDTOList(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( toDTO( category ) );
        }

        return list;
    }

    @Override
    public List<Category> toEntityList(List<CategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( dtoList.size() );
        for ( CategoryDTO categoryDTO : dtoList ) {
            list.add( toEntity( categoryDTO ) );
        }

        return list;
    }

    protected MediaDTO mediaToMediaDTO(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setName( media.getName() );
        byte[] fileContent = media.getFileContent();
        if ( fileContent != null ) {
            mediaDTO.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return mediaDTO;
    }
}
