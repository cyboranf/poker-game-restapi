package com.project.pokergame.service;

import com.project.pokergame.dto.GameSessionDTO;
import com.project.pokergame.mapper.GameSessionMapper;
import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.repository.GameRoomRepository;
import com.project.pokergame.repository.GameSessionRepository;
import javax.transaction.Transactional;
import com.project.pokergame.repository.UserProfileRepository;
import com.project.pokergame.validation.gameSession.GameSessionValidator;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@Transactional
public class GameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final GameRoomRepository gameRoomRepository;
    private final GameSessionMapper gameSessionMapper;
    private final UserProfileRepository userProfileRepository;
    private final GameSessionValidator gameSessionValidator;

    public GameSessionService(GameSessionRepository gameSessionRepository, GameRoomRepository gameRoomRepository, GameSessionMapper gameSessionMapper, UserProfileRepository userProfileRepository, GameSessionValidator gameSessionValidator) {
        this.gameSessionRepository = gameSessionRepository;
        this.gameRoomRepository = gameRoomRepository;
        this.gameSessionMapper = gameSessionMapper;
        this.userProfileRepository = userProfileRepository;
        this.gameSessionValidator = gameSessionValidator;
    }

    /**
     * Method to:
     * Start gameSession by creator
     */
    public GameSessionDTO startGameSession(Long roomId, Long userId) {
        GameRoom roomToStartSession = gameRoomRepository.findById(roomId).orElseThrow();
        gameSessionValidator.validateStartGameSession(roomToStartSession, userId);

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
        GameSession updatedSession = gameSessionRepository.findById(sessionId).orElseThrow();
        gameSessionValidator.validateUpdatePotValue(updatedSession, userId, amount);

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
        GameSession gameSession = gameSessionRepository.findById(sessionId).orElseThrow();
        UserProfile userProfile = userProfileRepository.findById(userId).orElseThrow();
        gameSessionValidator.validateEndGameSession(gameSession, userProfile);

        gameSession.setEndedAt(LocalDateTime.now());
        GameSession savedSession = gameSessionRepository.save(gameSession);

        return gameSessionMapper.toDTO(savedSession);
    }
}
