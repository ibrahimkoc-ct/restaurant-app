package com.ba.service;

import com.ba.builder.RoleBuilder;
import com.ba.builder.RoleDTOBuilder;
import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    @InjectMocks
    private RoleService service;

    @Mock
    private RoleRepository repository;
    RoleBuilder builder= new RoleBuilder();
    Role role=builder.id(1L).name("admin").build();
    RoleDTOBuilder dtoBuilder=new RoleDTOBuilder();
    RoleDTO dto=dtoBuilder.id(1L).name("admin").build();

    @Test
    public void addRoleTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(dto);
        RoleDTO result=service.addRole(dto);
        assertEquals(dto,result);
    }
    @Test(expected =RuntimeException.class)
    public void deleteRoleTest(){
        Long id=1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result=service.deleteById(id);
        assertEquals(result,"kisi silindi");
    }
    @Test
    public void updateRoleTest(){
        Mockito.when(repository.saveAndFlush(role)).thenReturn(role);
        RoleDTO result=service.updateRole(dto);
        assertEquals(result,dto);
    }
    @Test
    public void getAllRoleTest(){
        List<Role> roleList=new ArrayList<>();
        List<RoleDTO> roleDTOList=new ArrayList<>();
        roleDTOList.add(dto);
        roleList.add(role);
        Mockito.when(repository.findAll()).thenReturn(roleList);
        List<RoleDTO> result=service.getAllRole();
        assertEquals(result.get(0).getId(),roleList.get(0).getId());
    }
    @Test
    public void getRoleById(){
        Long id=1L;
        Optional<Role> optionalRole=Optional.of(role);
        Mockito.when(repository.findById(id)).thenReturn(optionalRole);
        RoleDTO result=service.getRoleById(id);
        assertEquals(result.getId(),id);
    }}