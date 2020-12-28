package com.ba.controller;

import com.ba.builder.RoleBuilder;
import com.ba.builder.RoleDTOBuilder;
import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.exception.BussinessRuleException;
import com.ba.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {
    @InjectMocks
    private RoleController controller;

    @Mock
    private RoleService service;
    RoleBuilder builder = new RoleBuilder();
    Role role = builder.id(1L).name("admin").build();
    RoleDTOBuilder dtoBuilder = new RoleDTOBuilder();
    RoleDTO dto = dtoBuilder.id(1L).name("admin").build();
    List<Role> roleList = new ArrayList<>();
    List<RoleDTO> roleDTOList = new ArrayList<>();

    @Test
    public void addRoleDTOControllerTest() {
        dto.setId(null);
        Mockito.when(service.addRole(dto)).thenReturn(dto);
        String result = controller.addRole(dto);
        assertEquals(result, "rol eklendi");

    }

    @Test
    public void deleteRoleDTOControllerTest() {
        Long id = 1L;
        Mockito.when(service.deleteById(id)).thenReturn("rol silindi");
        String result = controller.deleteRole(id);
        assertEquals(result, "rol silindi");
    }

    @Test
    public void getAllRoleDTOControllerTest() {
        roleDTOList.add(dto);
        Mockito.when(service.getAllRole()).thenReturn(roleDTOList);
        List<RoleDTO> result = controller.getAllRole();
        assertEquals(result.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void updateRoleDTOControllerTest() {
        Mockito.when(service.updateRole(dto)).thenReturn(dto);
        RoleDTO result = controller.updateRole(dto);
        assertEquals(dto, result);
    }

    @Test
    public void getRoleByIdControllerTest() {
        Long id = 1L;
        Mockito.when(service.getRoleById(id)).thenReturn(dto);
        RoleDTO result = controller.getRoleById(id);
        assertEquals(result, dto);
    }
    @Test(expected = BussinessRuleException.class)
    public void deleteRoleIdNullTest() {
        controller.deleteRole(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteRoleIdTest() {
        controller.deleteRole(-1L);
    }

    @Test(expected = BussinessRuleException.class)
    public void addRoleIdNullTest() {
        dto.setId(1L);
        controller.addRole(dto);
    }

    @Test(expected = BussinessRuleException.class)
    public void addRoleNullTest() {
        controller.addRole(null);
    }
    @Test(expected = BussinessRuleException.class)
    public void getRoleIdNullTest() {
        controller.getRoleById(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getRoleIdTest() {
        controller.getRoleById(-1L);
    }
    @Test(expected = BussinessRuleException.class)
    public void updateRoleNullTest() {
        controller.updateRole(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void updateRoleIdNullTest() {
        dto.setId(null);
        controller.updateRole(dto);
    }
}