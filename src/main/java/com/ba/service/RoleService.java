package com.ba.service;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.mapper.RoleMapper;
import com.ba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;


    public List<RoleDTO> getAllRole(){
        List<Role> roleList=repository.findAll();
        return RoleMapper.INSTANCE.toDTOList(roleList);
    }
    public String deleteById(Long id){
        Role role= repository.findById(id).get();
        repository.deleteById(id);
        return "kisi silindi";
    }
    public RoleDTO updateRole(RoleDTO role){
        repository.saveAndFlush(RoleMapper.INSTANCE.toEntity(role));
        return role;
    }
    public RoleDTO getRoleById(Long id){
        Role role =repository.findById(id).get();
        return RoleMapper.INSTANCE.toDTO(role);

    }
    public RoleDTO addRole(RoleDTO dto){
        repository.save(RoleMapper.INSTANCE.toEntity(dto));
        return dto;
    }

}
