package com.project.pokergame.dto;

import lombok.Data;

@Data
public class LeaderboardDTO {
    private Long id;
    private Long userAccountId;
    private Integer rank;
    private Double score;
}
