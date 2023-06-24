package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.RewardType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RewardDTO {
    private Long id;
    private Long userAccountId;
    private RewardType type;
    private LocalDateTime awardedAt;
}
