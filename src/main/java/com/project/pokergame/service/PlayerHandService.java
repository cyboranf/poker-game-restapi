package com.project.pokergame.service;

import com.project.pokergame.repository.PlayerHandRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerHandService {
    private final PlayerHandRepository playerHandRepository;

    public PlayerHandService(PlayerHandRepository playerHandRepository) {
        this.playerHandRepository = playerHandRepository;
    }
}
