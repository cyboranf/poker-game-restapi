package com.project.pokergame.exception.gameRoom;

public class UserAlreadyInRoomException extends RuntimeException {
    public UserAlreadyInRoomException(String message) {
        super(message);
    }
}
