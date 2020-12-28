package com.ba.service;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.RoleMapper;
import com.ba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;


    public List<RoleDTO> getAllRole() {
        List<Role> roleList = repository.findAll();
        if (roleList.isEmpty()) {
            throw new SystemException("Role not found");
        }
        return RoleMapper.INSTANCE.toDTOList(roleList);
    }
    public String deleteById(Long id) {

        repository.deleteById(id);
        return "kisi silindi";

    }
    public RoleDTO updateRole(RoleDTO role) {
        Optional<Role> optionalRole = repository.findById(role.getId());
        if (optionalRole==null) {
            throw new SystemException("Customer not found in database");
        }
        UpdateHelper.updateRoleHelper(role, optionalRole);
        repository.saveAndFlush(optionalRole.get());
        return RoleMapper.INSTANCE.toDTO(optionalRole.get());
    }

    public RoleDTO getRoleById(Long id) {
        Optional<Role> optionalRole = repository.findById(id);
        if (optionalRole==null) {
            throw new SystemException("Customer not found in database");
        }
            return RoleMapper.INSTANCE.toDTO(optionalRole.get());
    }
    public RoleDTO addRole(RoleDTO dto) {
        repository.save(RoleMapper.INSTANCE.toEntity(dto));
        return dto;
    }
}
