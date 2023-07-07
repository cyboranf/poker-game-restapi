package com.project.pokergame.service;

import com.project.pokergame.dto.PlayerSessionDTO;
import com.project.pokergame.mapper.PlayerSessionMapper;
import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.PlayerSession;
import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.repository.GameSessionRepository;
import com.project.pokergame.repository.PlayerSessionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.project.pokergame.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class PlayerSessionService {
    private final PlayerSessionRepository playerSessionRepository;
    private final UserProfileRepository userProfileRepository;
    private final GameSessionRepository gameSessionRepository;
    private final PlayerSessionMapper playerSessionMapper;

    public PlayerSessionService(PlayerSessionRepository playerSessionRepository, UserProfileRepository userProfileRepository, GameSessionRepository gameSessionRepository, PlayerSessionMapper playerSessionMapper) {
        this.playerSessionRepository = playerSessionRepository;
        this.userProfileRepository = userProfileRepository;
        this.gameSessionRepository = gameSessionRepository;
        this.playerSessionMapper = playerSessionMapper;
    }

    /**
     * Method to create PlayerSession
     */
    public PlayerSessionDTO createPlayerSession(Long userId, Long gameId, Double stackSize) {
        PlayerSession createdPlayerSession = new PlayerSession();
        UserAccount joiner = userProfileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Can not found user with id = " + userId))
                .getUserAccount();

        GameSession gameSession = gameSessionRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Can not found game session with id = " + gameId));


        createdPlayerSession.setUserAccount(joiner);
        createdPlayerSession.setGameSession(gameSession);
        createdPlayerSession.setStackSize(stackSize);

        List<PlayerSession> existingPlayerSessions = playerSessionRepository.findByGameSession(gameSession);

        Set<Integer> takenSeats = existingPlayerSessions.stream()
                .map(PlayerSession::getSeatNumber)
                .collect(Collectors.toSet());

        int maxPlayers = gameSession.getGameRoom().getMaxPlayers();

        Integer seatNumber = IntStream.range(1, maxPlayers + 1)
                .filter(seat -> !takenSeats.contains(seat))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No available seats"));

        createdPlayerSession.setSeatNumber(seatNumber);
        createdPlayerSession.setIsDealer(false);

        PlayerSession savedPlayerSession = playerSessionRepository.save(createdPlayerSession);

        return playerSessionMapper.toDTO(savedPlayerSession);
    }

    /**
     * Method to join Player to Session
     */
//    public PlayerSession joinPlayerToSession(Long userId, Long sessionId) {
//        PlayerSession session = playerSessionRepository.findById(sessionId)
//                .orElseThrow(() -> new EntityNotFoundException("Can not found session with id = " + sessionId));
//
//        UserProfile user = userProfileRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id = " + userId));
//
//
//    }
}
