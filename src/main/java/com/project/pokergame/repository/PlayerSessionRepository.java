package com.project.pokergame.repository;

import com.project.pokergame.model.PlayerSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerSessionRepository extends JpaRepository<PlayerSession, Long> {
}
