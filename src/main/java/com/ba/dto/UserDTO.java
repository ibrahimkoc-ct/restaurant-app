package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull(message = "User email cannot null")
    private String email;
    @NotNull(message = "User username cannot null")
    private String username;
    @NotNull(message = "User password cannot null")
    private String password;
    @NotNull(message = "User enabled cannot null")
    private boolean enabled;
    @NotNull(message = "User roles cannot null")
    private List<RoleDTO> roles=new ArrayList<>();

}
