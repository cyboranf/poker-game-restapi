package com.project.pokergame.repository;

import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    GameSession findByGameRoomAndEndedAtIsNull(GameRoom gameRoom);
}
