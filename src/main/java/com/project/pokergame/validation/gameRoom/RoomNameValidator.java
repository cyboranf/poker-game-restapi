package com.project.pokergame.validation.gameRoom;

import org.springframework.stereotype.Component;

@Component
public class RoomNameValidator {
    public void validate(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Game room must not exceed 20 characters and can not be empty.");
        }
    }
}
