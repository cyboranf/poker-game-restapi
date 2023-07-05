package com.project.pokergame.validation.gameRoom;

import com.project.pokergame.dto.GameRoomDTO;
import org.springframework.stereotype.Component;

@Component
public class GameRoomValidator {
    private final RoomNameValidator roomNameValidator;
    private final RoomMaxPlayersValidator roomMaxPlayersValidator;
    private final SmallBlindValidator smallBlindValidator;
    private final BigBlindValidator bigBlindValidator;

    public GameRoomValidator(RoomNameValidator roomNameValidator, RoomMaxPlayersValidator roomMaxPlayersValidator, SmallBlindValidator smallBlindValidator, BigBlindValidator bigBlindValidator) {
        this.roomNameValidator = roomNameValidator;
        this.roomMaxPlayersValidator = roomMaxPlayersValidator;
        this.smallBlindValidator = smallBlindValidator;
        this.bigBlindValidator = bigBlindValidator;
    }
    public  void validate(GameRoomDTO gameRoomDTO) {
        roomNameValidator.validate(gameRoomDTO.getName());
        roomMaxPlayersValidator.validate(gameRoomDTO.getMaxPlayers());
        smallBlindValidator.validate(gameRoomDTO.getSmallBlind());
        bigBlindValidator.validate(gameRoomDTO.getBigBlind(), gameRoomDTO.getSmallBlind());
    }
}
