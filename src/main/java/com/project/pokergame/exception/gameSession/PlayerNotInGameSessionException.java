package com.project.pokergame.exception.gameSession;

public class PlayerNotInGameSessionException extends RuntimeException {
    public PlayerNotInGameSessionException(String message) {
        super(message);
    }
}
