package com.project.pokergame.mapper;

import com.project.pokergame.dto.UserAccountDTO;
import com.project.pokergame.model.Role;
import com.project.pokergame.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserAccountMapper {
    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);

    @Mapping(source = "roles", target = "roleIds", qualifiedByName = "mapRolesToRoleIds")
    UserAccountDTO userAccountToUserAccountDTO(UserAccount userAccount);

    @Mapping(source = "roleIds", target = "roles", ignore = true)
    UserAccount userAccountDTOToUserAccount(UserAccountDTO userAccountDTO);

    @Named("mapRolesToRoleIds")
    default Set<Integer> mapRolesToRoleIds(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Role::getId)
                .collect(Collectors.toSet());
    }
}
