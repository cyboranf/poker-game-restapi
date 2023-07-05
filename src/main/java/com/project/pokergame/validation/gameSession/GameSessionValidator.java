package com.project.pokergame.validation.gameSession;

import com.project.pokergame.exception.gameSession.*;
import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.model.enumerated.RoomStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameSessionValidator {

    public void validateStartGameSession(GameRoom roomToStartSession, Long userId) {
        if (!(roomToStartSession.getStatus().equals(RoomStatus.ACTIVE))) {
            throw new IllegalStateException("Game room must be in ACTIVE status to start a game session.");
        }
        if (!(roomToStartSession.getPlayers().size() >= 3)) {
            throw new InsufficientPlayersException("There must be at least 3 players to start a game session.");
        }
        if (!(roomToStartSession.getCreator().getId().equals(userId))) {
            throw new UnauthorizedActionException("Only the creator of the room is allowed to start the game session.");
        }
    }

    public void validateUpdatePotValue(GameSession updatedSession, Long userId, Double amount) {
        if (!(updatedSession.getEndedAt() == null)) {
            throw new GameSessionAlreadyEndedException("Game session has already ended and cannot be modified.");
        }
        List<UserProfile> players = updatedSession.getGameRoom().getPlayers();
        boolean isPlayerInGameSession = players.stream().anyMatch(p -> p.getId().equals(userId));
        if (!isPlayerInGameSession) {
            throw new PlayerNotInGameSessionException("The user is not part of the game session.");
        }
        if (amount < updatedSession.getGameRoom().getSmallBlind()) {
            throw new MinimumAmountNotMetException("You cannot add " + amount + " to pot, because It is less than small blind.");
        }
        if (amount < updatedSession.getLastCall()) {
            throw new CallAmountNotMetException("You must at least match the last call amount to continue playing.");
        }
    }

    public void validateEndGameSession(GameSession gameSession, UserProfile userProfile) {
        if (gameSession.getEndedAt() != null) {
            throw new GameSessionAlreadyEndedException("Game session has already ended.");
        }

        boolean isCreator = gameSession.getGameRoom().getCreator().getId().equals(userProfile.getId());
        boolean isAdminOrMod = userProfile.getUserAccount().getRoles().stream()
                .anyMatch(role -> role.getName().equals("ADMIN") || role.getName().equals("MOD"));

        if (!isCreator && !isAdminOrMod) {
            throw new UnauthorizedActionException("Only the creator of the room, ADMIN or MOD can end the game session.");
        }
    }
}
