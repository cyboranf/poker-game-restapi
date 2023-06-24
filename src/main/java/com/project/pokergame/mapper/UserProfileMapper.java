package com.project.pokergame.mapper;

import com.project.pokergame.dto.UserProfileDTO;
import com.project.pokergame.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    UserProfileDTO userProfileToUserProfileDTO(UserProfile userProfile);

    @Mapping(source = "userAccountId", target = "userAccount", ignore = true)
    UserProfile userProfileDTOToUserProfile(UserProfileDTO userProfileDTO);
}