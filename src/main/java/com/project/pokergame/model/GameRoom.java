package com.project.pokergame.model;

import com.project.pokergame.model.enumerated.RoomStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_room")
@Data
@NoArgsConstructor
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "max_players")
    private Integer maxPlayers;

    @Column(name = "small_blind")
    private Double smallBlind;

    @Column(name = "big_blind")
    private Double bigBlind;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
