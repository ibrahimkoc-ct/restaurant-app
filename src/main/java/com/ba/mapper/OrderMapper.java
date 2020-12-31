package com.ba.mapper;

import com.ba.dto.OrderItemDTO;
import com.ba.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderItem toEntity(OrderItemDTO orderItemDTO);
    OrderItemDTO toDTO(OrderItem orderItem);
    List<OrderItem> toEntityList(List<OrderItemDTO> orderItemDTOList);
    List<OrderItemDTO> toDTOList(List<OrderItem> orderItemList);
}
