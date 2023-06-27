package com.project.pokergame.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "player_session")
@Data
@NoArgsConstructor
public class PlayerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "game_session_id", referencedColumnName = "id")
    private GameSession gameSession;

    @Column(name = "stack_size")
    private Double stackSize;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "is_dealer")
    private Boolean isDealer;
}
