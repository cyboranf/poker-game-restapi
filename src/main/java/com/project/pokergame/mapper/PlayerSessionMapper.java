package com.project.pokergame.mapper;

import com.project.pokergame.dto.PlayerSessionDTO;
import com.project.pokergame.model.PlayerSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerSessionMapper {
    PlayerSessionMapper INSTANCE = Mappers.getMapper(PlayerSessionMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    @Mapping(source = "gameSession.id", target = "gameSessionId")
    PlayerSessionDTO playerSessionToPlayerSessionDTO(PlayerSession playerSession);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    @Mapping(source = "gameSessionId", target = "gameSession.id")
    PlayerSession playerSessionDTOToPlayerSession(PlayerSessionDTO playerSessionDTO);
}
