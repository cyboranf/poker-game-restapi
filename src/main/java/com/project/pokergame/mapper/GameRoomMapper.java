package com.project.pokergame.mapper;

import com.project.pokergame.dto.GameRoomDTO;
import com.project.pokergame.model.GameRoom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameRoomMapper {
    GameRoomMapper INSTANCE = Mappers.getMapper(GameRoomMapper.class);

    GameRoomDTO toDTO(GameRoom gameRoom);

    GameRoom DTO2GameRoom(GameRoomDTO gameRoomDTO);
}
