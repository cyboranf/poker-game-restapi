package com.project.pokergame.validation.gameRoom;

public class BigBlindValidator {
    public static void validate(Double bigBlind, Double smallBlind) {
        if (bigBlind < smallBlind * 2 || bigBlind > 20000.0) {
            throw new IllegalArgumentException("The big blind value must be at least twice the small blind value and not exceed 20000.0.");
        }
    }
}
