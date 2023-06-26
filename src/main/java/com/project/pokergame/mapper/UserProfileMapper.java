package com.project.pokergame.mapper;

import com.project.pokergame.dto.UserProfileDTO;
import com.project.pokergame.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(source = "userAccount.id", target = "userAccountId")
    UserProfileDTO toDTO(UserProfile userProfile);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    UserProfile DTO2UserProfile(UserProfileDTO userProfileDTO);
}
