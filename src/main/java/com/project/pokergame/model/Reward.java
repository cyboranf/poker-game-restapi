package com.project.pokergame.model;

import com.project.pokergame.model.enumerated.RewardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reward")
@Data
@NoArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    @Enumerated(EnumType.STRING)
    private RewardType type;

    @Column(name = "awarded_at")
    private LocalDateTime awardedAt;
}
