package com.project.pokergame.mapper;

import com.project.pokergame.dto.RoleDTO;
import com.project.pokergame.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role RoleDTO2Role(RoleDTO roleDTO);
}
