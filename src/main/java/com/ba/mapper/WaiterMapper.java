package com.ba.mapper;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


public interface WaiterMapper {
    WaiterMapper INSTANCE = Mappers.getMapper(WaiterMapper.class);
    @Mapping(source ="media", target = "mediaDTO")
    WaiterDTO toDTO(Waiter waiter);

    @Mapping(source = "mediaDTO",target = "media")
    Waiter toEntity(WaiterDTO dto);

    List<WaiterDTO> toDTOList(List<Waiter> waiterList);


    List<Waiter> toEntityList(List<WaiterDTO> dtoList);

}
