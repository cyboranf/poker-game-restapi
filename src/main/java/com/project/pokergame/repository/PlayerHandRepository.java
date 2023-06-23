package com.project.pokergame.repository;

import com.project.pokergame.model.PlayerHand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerHandRepository extends JpaRepository<PlayerHand, Long> {
}
