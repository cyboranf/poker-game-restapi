package com.project.pokergame.exception.gameSession;

public class InsufficientPlayersException extends RuntimeException {
    public InsufficientPlayersException(String message) {
        super(message);
    }
}