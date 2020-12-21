package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private List<RoleDTO> roles=new ArrayList<>();

}
