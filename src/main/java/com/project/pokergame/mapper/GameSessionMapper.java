package com.project.pokergame.mapper;

import com.project.pokergame.dto.GameSessionDTO;
import com.project.pokergame.model.GameSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameSessionMapper {
    GameSessionMapper INSTANCE = Mappers.getMapper(GameSessionMapper.class);

    @Mapping(source = "gameRoom.id", target = "gameRoomId")
    GameSessionDTO gameSessionToGameSessionDTO(GameSession gameSession);

    @Mapping(source = "gameRoomId", target = "gameRoom.id")
    GameSession gameSessionDTOToGameSession(GameSessionDTO gameSessionDTO);
}
