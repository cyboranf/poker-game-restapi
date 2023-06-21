package com.project.pokergame.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "player_hand")
@Data
@NoArgsConstructor
public class PlayerHand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_session_id", referencedColumnName = "id")
    private PlayerSession playerSession;

    @ManyToOne
    @JoinColumn(name = "card1_id", referencedColumnName = "id")
    private Card card1;

    @ManyToOne
    @JoinColumn(name = "card2_id", referencedColumnName = "id")
    private Card card2;
}
