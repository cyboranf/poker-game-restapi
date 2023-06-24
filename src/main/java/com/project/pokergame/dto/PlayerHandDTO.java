package com.project.pokergame.dto;

import lombok.Data;

@Data
public class PlayerHandDTO {
    private Long id;
    private Long playerSessionId;
    private CardDTO card1;
    private CardDTO card2;
}