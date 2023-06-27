package com.project.pokergame.service;

import com.project.pokergame.repository.GameRoomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameRoomService {
    private final GameRoomRepository gameRoomRepository;

    public GameRoomService(GameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }
}
