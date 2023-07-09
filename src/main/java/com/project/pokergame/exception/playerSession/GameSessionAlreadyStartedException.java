package com.project.pokergame.exception.playerSession;

public class GameSessionAlreadyStartedException extends RuntimeException {
    public GameSessionAlreadyStartedException(String message) {
        super(message);
    }
}
