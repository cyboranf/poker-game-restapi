package com.project.pokergame.mapper;

import com.project.pokergame.dto.GameSessionDTO;
import com.project.pokergame.model.GameSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameSessionMapper {
    GameSessionMapper INSTANCE = Mappers.getMapper(GameSessionMapper.class);

    @Mapping(source = "gameRoom.id", target = "gameRoomId")
    GameSessionDTO toDTO(GameSession gameSession);

    @Mapping(target = "gameRoom", ignore = true)
    GameSession DTO2GameSession(GameSessionDTO gameSessionDTO);
}
