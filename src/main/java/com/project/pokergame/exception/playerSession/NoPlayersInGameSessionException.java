package com.project.pokergame.exception.playerSession;

public class NoPlayersInGameSessionException extends RuntimeException {
    public NoPlayersInGameSessionException(String message) {
        super(message);
    }
}
