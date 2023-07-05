package com.project.pokergame.validation.gameRoom;

import org.springframework.stereotype.Component;

@Component
public class SmallBlindValidator {
    public void validate(Double smallBlind) {
        if (smallBlind < 1.0 || smallBlind > 10000.0) {
            throw new IllegalArgumentException("The small blind value must be between 1.0 and 10000.0.");
        }
    }
}
