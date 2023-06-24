package com.project.pokergame.mapper;

import com.project.pokergame.dto.RewardDTO;
import com.project.pokergame.model.Reward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RewardMapper {
    RewardMapper INSTANCE = Mappers.getMapper(RewardMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    RewardDTO rewardToRewardDTO(Reward reward);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    Reward rewardDTOToReward(RewardDTO rewardDTO);
}
