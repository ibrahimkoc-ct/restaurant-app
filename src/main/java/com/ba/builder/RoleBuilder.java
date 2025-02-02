package com.ba.builder;

import com.ba.entity.Role;

public class RoleBuilder extends IdBuilder {
    private String name;

    public RoleBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public RoleBuilder name(String name){
        this.name =name;
        return this;
    }

    public Role build() {
        Role role=  new Role();
        role.setId(this.getId());
        role.setName(this.name);
        return role;
    }
}
