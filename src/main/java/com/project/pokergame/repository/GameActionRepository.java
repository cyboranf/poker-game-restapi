package com.project.pokergame.repository;

import com.project.pokergame.model.GameAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameActionRepository extends JpaRepository<GameAction, Long> {
}
