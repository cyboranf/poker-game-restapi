package com.project.pokergame.mapper;

import com.project.pokergame.dto.RewardDTO;
import com.project.pokergame.model.Reward;
import com.project.pokergame.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RewardMapper {
    RewardMapper INSTANCE = Mappers.getMapper(RewardMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    RewardDTO toDTO(Reward reward);

    @Mapping(target = "userAccount", ignore = true)
    Reward DTO2Reward(RewardDTO rewardDTO);

    default UserAccount mapUserAccount(Long value) {
        if (value == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(value);
        return userAccount;
    }
}
