package com.project.pokergame.model;

import com.project.pokergame.model.enumerated.ActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_actions")
@Data
@NoArgsConstructor
public class GameAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_session_id", referencedColumnName = "id")
    private GameSession gameSession;

    @ManyToOne
    @JoinColumn(name = "player_session_id", referencedColumnName = "id")
    private PlayerSession playerSession;

    @Enumerated(EnumType.STRING)
    private ActionType type;

    private Double amount;

    @Column(name = "action_time")
    private LocalDateTime actionTime;
}

