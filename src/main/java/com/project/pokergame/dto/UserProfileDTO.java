package com.project.pokergame.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private Long userAccountId;
    private String firstName;
    private String lastName;
    private String avatar;
    private String country;
    private String bio;
    private Integer totalGamesPlayed;
    private Double totalWinnings;
}
