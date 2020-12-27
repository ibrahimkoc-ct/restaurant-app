package com.ba.builder;

import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Customer;

public class CustomerDTOBuilder extends IdBuilder {
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private MediaDTO mediaDTO;

    public CustomerDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public CustomerDTOBuilder name(String name){
        this.name =name;
        return this;
    }
    public CustomerDTOBuilder surname(String surname){
        this.surname =surname;
        return this;
    }
    public CustomerDTOBuilder address(String address){
        this.address =address;
        return this;
    }
    public CustomerDTOBuilder phoneNumber(String phoneNumber){
        this.phoneNumber =phoneNumber;
        return this;
    }
    public CustomerDTOBuilder mediaDTO(MediaDTO mediaDTO){
        this.mediaDTO =mediaDTO;
        return this;
    }

    @Override
    public CustomerDTO build() {
        CustomerDTO dto = new CustomerDTO(this.getId(),name,surname,phoneNumber,address,mediaDTO);
        return dto;
    }
}
