package com.project.pokergame.service;

import com.project.pokergame.dto.GameSessionDTO;
import com.project.pokergame.exception.gameSession.*;
import com.project.pokergame.mapper.GameSessionMapper;
import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.model.enumerated.Role;
import com.project.pokergame.model.enumerated.RoomStatus;
import com.project.pokergame.repository.GameRoomRepository;
import com.project.pokergame.repository.GameSessionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.project.pokergame.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class GameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final GameRoomRepository gameRoomRepository;
    private final GameSessionMapper gameSessionMapper;
    private final UserProfileRepository userProfileRepository;

    public GameSessionService(GameSessionRepository gameSessionRepository, GameRoomRepository gameRoomRepository, GameSessionMapper gameSessionMapper, UserProfileRepository userProfileRepository) {
        this.gameSessionRepository = gameSessionRepository;
        this.gameRoomRepository = gameRoomRepository;
        this.gameSessionMapper = gameSessionMapper;
        this.userProfileRepository = userProfileRepository;
    }

    /**
     * Method to:
     * Start gameSession by creator
     */
    public GameSessionDTO startGameSession(Long roomId, Long userId) {
        GameRoom roomToStartSession = gameRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Game room not found with id = " + roomId));
        if (!(roomToStartSession.getStatus().equals(RoomStatus.ACTIVE))) {
            throw new IllegalStateException("Game room must be in ACTIVE status to start a game session.");
        }
        if (!(roomToStartSession.getPlayers().size() >= 3)) {
            throw new InsufficientPlayersException("There must be at least 3 players to start a game session.");
        }
        if (!(roomToStartSession.getCreator().getId().equals(userId))) {
            throw new UnauthorizedActionException("Only the creator of the room is allowed to start the game session.");
        }
        if (gameSessionRepository.findByGameRoomAndEndedAtIsNull(roomToStartSession) != null) {
            throw new IllegalStateException("A game session is already running for this room.");
        }
        GameSession startedSession = new GameSession();
        startedSession.setGameRoom(roomToStartSession);
        startedSession.setStartedAt(LocalDateTime.now());
        startedSession.setEndedAt(null);
        startedSession.setPotSize(0.0);

        GameSession savedSession = gameSessionRepository.save(startedSession);

        return gameSessionMapper.toDTO(savedSession);
    }

    /**
     * Method to:
     * updatePotValue
     */
    public GameSessionDTO updatePotValue(Long sessionId, Long userId, Double amount) {
        GameSession updatedSession = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Game session not found with id = " + sessionId));
        if (!(updatedSession.getEndedAt() == null)) {
            throw new GameSessionAlreadyEndedException("Game session has already ended and cannot be modified.");
        }
        List<UserProfile> players = updatedSession.getGameRoom().getPlayers();
        boolean isPlayerInGameSession = players.stream()
                .anyMatch(p -> p.getId().equals(userId));
        if (!isPlayerInGameSession) {
            throw new PlayerNotInGameSessionException("The user is not part of the game session.");
        }
        if (amount < updatedSession.getGameRoom().getSmallBlind()) {
            throw new MinimumAmountNotMetException("You can not add " + amount + " to pot, because It is less than small blind.");
        }
        if (amount < updatedSession.getLastCall()) {
            throw new CallAmountNotMetException("You must at least match the last call amount to continue playing.");
        }

        updatedSession.setPotSize(updatedSession.getPotSize() + amount);
        GameSession savedSession = gameSessionRepository.save(updatedSession);

        return gameSessionMapper.toDTO(savedSession);
    }

    /**
     * Method to:
     * End Game Session
     * By creator, ADMIN and MOD
     */
    public GameSessionDTO endGameSession(Long sessionId, Long userId) {
        GameSession gameSession = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Game session not found with id = " + sessionId));

        if (gameSession.getEndedAt() != null) {
            throw new GameSessionAlreadyEndedException("Game session has already ended.");
        }

        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id = " + userId));

        if (!isUserAuthorizedToEndGame(userProfile, gameSession)) {
            throw new UnauthorizedActionException("Only the creator of the room, ADMIN or MOD can end the game session.");
        }

        gameSession.setEndedAt(LocalDateTime.now());
        GameSession savedSession = gameSessionRepository.save(gameSession);

        return gameSessionMapper.toDTO(savedSession);
    }


    private boolean isUserAuthorizedToEndGame(UserProfile userProfile, GameSession gameSession) {
        boolean isCreator = gameSession.getGameRoom().getCreator().getId().equals(userProfile.getId());
        boolean isAdminOrMod = userProfile.getUserAccount().getRoles().stream()
                .anyMatch(role -> role.getName().equals(Role.ADMIN) || role.getName().equals(Role.MOD));

        return isCreator || isAdminOrMod;
    }
}
