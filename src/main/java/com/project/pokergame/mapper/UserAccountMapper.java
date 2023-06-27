package com.project.pokergame.mapper;

import com.project.pokergame.dto.UserAccountDTO;
import com.project.pokergame.model.Role;
import com.project.pokergame.model.UserAccount;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public abstract class UserAccountMapper {

    public abstract UserAccountDTO toDTO(UserAccount userAccount);

    public abstract UserAccount DTO2UserAccount(UserAccountDTO userAccountDTO);

    @AfterMapping
    protected void mapRolesToRoleIds(UserAccount userAccount, @MappingTarget UserAccountDTO userAccountDTO) {
        if (userAccount.getRoles() != null) {
            Set<Long> roleIds = new HashSet<>();
            for (Role role : userAccount.getRoles()) {
                roleIds.add((long) role.getId());
            }
            userAccountDTO.setRoleIds(roleIds);
        }
    }

    @AfterMapping
    protected void mapRoleIdsToRoles(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount) {
        if (userAccountDTO.getRoleIds() != null) {
            Set<Role> roles = new HashSet<>();
            for (Long roleId : userAccountDTO.getRoleIds()) {
                Role role = new Role();
                role.setId(roleId.intValue());
                roles.add(role);
            }
            userAccount.setRoles(roles);
        }
    }
}
