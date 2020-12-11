package com.ba.service;

import com.ba.converter.RoleDTOConverter;
import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;


    public List<RoleDTO> getAllRole(){
        List<Role> roleList=repository.findAll();
        return RoleDTOConverter.roleListToRoleDTOList(roleList);
    }
    public String deleteById(Long id){
        Role role= repository.findById(id).get();
        repository.deleteById(id);
        return "kisi silindi";
    }
    public RoleDTO updateRole(RoleDTO role){
       repository.saveAndFlush(RoleDTOConverter.roleDTOtoRole(role));
        return role;
    }
    public RoleDTO getRoleById(Long id){
        Optional<Role> list=repository.findById(id);
        return RoleDTOConverter.getRoleById(list);
    }
    public RoleDTO addRole(RoleDTO dto){
        repository.save(RoleDTOConverter.roleDTOtoRole(dto));
        return dto;
    }




}
