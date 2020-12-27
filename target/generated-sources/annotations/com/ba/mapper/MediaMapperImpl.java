package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-26T22:03:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class MediaMapperImpl implements MediaMapper {

    @Override
    public MediaDTO toDTO(Media media) {
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

    @Override
    public Media toEntity(MediaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( dto.getId() );
        media.setName( dto.getName() );
        byte[] fileContent = dto.getFileContent();
        if ( fileContent != null ) {
            media.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return media;
    }

    @Override
    public List<Media> toEntityList(List<MediaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Media> list = new ArrayList<Media>( dtoList.size() );
        for ( MediaDTO mediaDTO : dtoList ) {
            list.add( toEntity( mediaDTO ) );
        }

        return list;
    }

    @Override
    public List<MediaDTO> toDTOList(List<Media> mediaList) {
        if ( mediaList == null ) {
            return null;
        }

        List<MediaDTO> list = new ArrayList<MediaDTO>( mediaList.size() );
        for ( Media media : mediaList ) {
            list.add( toDTO( media ) );
        }

        return list;
    }
}
