package com.project.pokergame.mapper;

import com.project.pokergame.dto.LeaderboardDTO;
import com.project.pokergame.model.Leaderboard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaderboardMapper {
    LeaderboardMapper INSTANCE = Mappers.getMapper(LeaderboardMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    LeaderboardDTO leaderboardToLeaderboardDTO(Leaderboard leaderboard);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    Leaderboard leaderboardDTOToLeaderboard(LeaderboardDTO leaderboardDTO);
}
