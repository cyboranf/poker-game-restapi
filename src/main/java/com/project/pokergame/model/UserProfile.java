package com.project.pokergame.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String avatar;
    private String country;
    private String bio;

    @Column(name = "total_games_played")
    private Integer totalGamesPlayed;

    @Column(name = "total_winnings")
    private Double totalWinnings;
}