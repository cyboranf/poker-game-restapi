package com.project.pokergame.mapper;

import com.project.pokergame.dto.GameActionDTO;
import com.project.pokergame.model.GameAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameActionMapper {
    GameActionMapper INSTANCE = Mappers.getMapper(GameActionMapper.class);

    @Mapping(source = "gameSession.id", target = "gameSessionId")
    @Mapping(source = "playerSession.id", target = "playerSessionId")
    GameActionDTO toDTO(GameAction gameAction);

    @Mapping(target = "gameSession", expression = "java(new com.project.pokergame.model.GameSession())")
    @Mapping(target = "gameSession.id", source = "gameSessionId")
    @Mapping(target = "playerSession", expression = "java(new com.project.pokergame.model.PlayerSession())")
    @Mapping(target = "playerSession.id", source = "playerSessionId")
    GameAction DTO2GameAction(GameActionDTO gameActionDTO);
}
