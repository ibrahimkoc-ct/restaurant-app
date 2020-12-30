package com.ba.controller;

import com.ba.dto.RoleDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/list")
    public List<RoleDTO> getAllRole() {
        return service.getAllRole();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        service.deleteById(id);
        return "rol silindi";
    }

    @PostMapping("/add")
    public String addRole(@Valid @RequestBody RoleDTO dto) {
        if (dto.getId() != null) {
            throw new BussinessRuleException("Role cannot be empty!");
        }
        service.addRole(dto);
        return "rol eklendi";
    }

    @PutMapping("/update/")
    public RoleDTO updateRole(@Valid @RequestBody RoleDTO dto) {
        if (dto.getId() == null) {
            throw new BussinessRuleException("Role cannot be empty!");
        }
        return service.updateRole(dto);
    }

    @GetMapping("/id/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return service.getRoleById(id);
    }

}
