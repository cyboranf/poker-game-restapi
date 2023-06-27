package com.project.pokergame.service;

import com.project.pokergame.repository.PlayerSessionRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerSessionService {
    private final PlayerSessionRepository playerSessionRepository;

    public PlayerSessionService(PlayerSessionRepository playerSessionRepository) {
        this.playerSessionRepository = playerSessionRepository;
    }
}
