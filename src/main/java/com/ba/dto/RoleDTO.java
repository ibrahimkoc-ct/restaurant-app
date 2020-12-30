package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    @NotNull(message = "Role name cannot null")
    private String name;

}
