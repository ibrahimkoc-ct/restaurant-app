package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MediaMapper {
    MediaMapper INSTANCE= Mappers.getMapper(MediaMapper.class);
    MediaDTO toDTO(Media media);
    Media toEntity(MediaDTO dto);
    List<Media> toEntityList(List<MediaDTO> dtoList);
    List<MediaDTO> toDTOList (List<Media> mediaList);
}
