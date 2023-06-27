package com.project.pokergame.service;

import com.project.pokergame.repository.GameActionRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GameActionService {
    private final GameActionRepository gameActionRepository;

    public GameActionService(GameActionRepository gameActionRepository) {
        this.gameActionRepository = gameActionRepository;
    }
}
