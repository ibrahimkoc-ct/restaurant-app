package com.ba.builder;

import com.ba.dto.RoleDTO;

public class RoleDTOBuilder extends IdBuilder {

    private String name;

    public RoleDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public RoleDTOBuilder name(String name){
        this.name =name;
        return this;
    }
    public RoleDTO build() {
        RoleDTO dto= new RoleDTO();
        dto.setName(this.name);
        dto.setId(this.getId());
        return dto;
    }
}
