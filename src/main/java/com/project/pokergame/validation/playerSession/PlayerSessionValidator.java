package com.project.pokergame.validation.playerSession;

import com.project.pokergame.exception.playerSession.GameSessionAlreadyStartedException;
import com.project.pokergame.exception.playerSession.NoPlayersInGameSessionException;
import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.PlayerSession;
import com.project.pokergame.repository.PlayerSessionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerSessionValidator {
    private final PlayerSessionRepository playerSessionRepository;

    public PlayerSessionValidator(PlayerSessionRepository playerSessionRepository) {
        this.playerSessionRepository = playerSessionRepository;
    }

    public void validateSelectDealer(GameSession gameSession) {
        if (gameSession.getStartedAt() != null) {
            throw new GameSessionAlreadyStartedException("Game session with id = " + gameSession.getId() + " has already started");
        }

        List<PlayerSession> playerSessions = playerSessionRepository.findByGameSession(gameSession);

        if (playerSessions.isEmpty()) {
            throw new NoPlayersInGameSessionException("No players in the game session with id = " + gameSession.getId());
        }
    }
}
