package com.project.pokergame.exception;

public class UserAlreadyInRoomException extends RuntimeException {
    public UserAlreadyInRoomException(String message) {
        super(message);
    }
}
