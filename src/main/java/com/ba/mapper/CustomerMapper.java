package com.ba.mapper;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CustomerMapper {
//    CustomerMapper INSTANCE =Mappers.getMapper(CustomerMapper.class);


    @Mapping(source ="media", target = "mediaDTO")
    CustomerDTO toDTO(Customer customer);
    @Mapping(source = "mediaDTO",target = "media")
    Customer toEntity(CustomerDTO dto);

    List<Customer> toEntityList(List<CustomerDTO> dtoList);
    List<CustomerDTO> toDTOList(List<Customer> list);

}
