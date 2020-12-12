package com.ba.controller;

import com.ba.dto.RoleDTO;
import com.ba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
   private RoleService service;

    @GetMapping("/list")
    public List<RoleDTO> getAllRole(){
        return service.getAllRole();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        service.deleteById(id);
        return "rol silindi";
    }
    @PostMapping("/add")
    public String addRole(@RequestBody RoleDTO dto){
        service.addRole(dto);
    return "rol eklendi";
    }
    @PutMapping("/update/")
    public RoleDTO updateRole(@RequestBody RoleDTO dto){
      return service.updateRole(dto);
    }
    @GetMapping("/id/{id}")
    public RoleDTO getRoleById(@PathVariable Long id){
       return service.getRoleById(id);
    }

}
