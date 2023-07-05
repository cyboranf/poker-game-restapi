package com.project.pokergame.exception.gameSession;

public class GameSessionAlreadyEndedException extends RuntimeException {
    public GameSessionAlreadyEndedException(String message) {
        super(message);
    }
}
