package com.project.pokergame.validation.gameRoom;

public class RoomMaxPlayersValidator {
    public static void validate(Integer maxPlayers) {
        if (maxPlayers < 2 || maxPlayers > 10) {
            throw new IllegalArgumentException("Room must have a minimum of 2 players and a maximum of 10 players.");
        }
    }
}
