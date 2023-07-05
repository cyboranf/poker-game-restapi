package com.project.pokergame.exception.gameRoom;

public class RoomFullException extends RuntimeException {
    public RoomFullException(String message) {
        super(message);
    }
}
