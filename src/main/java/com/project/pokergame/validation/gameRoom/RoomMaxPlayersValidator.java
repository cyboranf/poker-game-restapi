package com.project.pokergame.validation.gameRoom;

import org.springframework.stereotype.Component;

@Component
public class RoomMaxPlayersValidator {
    public void validate(Integer maxPlayers) {
        if (maxPlayers < 2 || maxPlayers > 10) {
            throw new IllegalArgumentException("Room must have a minimum of 2 players and a maximum of 10 players.");
        }
    }
}
