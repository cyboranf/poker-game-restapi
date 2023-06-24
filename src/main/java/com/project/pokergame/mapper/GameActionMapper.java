package com.project.pokergame.mapper;

import com.project.pokergame.dto.GameActionDTO;
import com.project.pokergame.model.GameAction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;


@Mapper
public interface GameActionMapper {
    GameActionMapper INSTANCE = Mappers.getMapper(GameActionMapper.class);

    @Mapping(source = "gameSession.id", target = "gameSessionId")
    @Mapping(source = "playerSession.id", target = "playerSessionId")
    GameActionDTO gameActionToGameActionDTO(GameAction gameAction);

    @Mapping(target = "gameSession", ignore = true)
    @Mapping(target = "playerSession", ignore = true)
    GameAction gameActionDTOToGameAction(GameActionDTO gameActionDTO);
}
