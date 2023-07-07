package com.project.pokergame.repository;

import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.PlayerSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerSessionRepository extends JpaRepository<PlayerSession, Long> {
    List<PlayerSession> findByGameSession(GameSession gameSession);

}
