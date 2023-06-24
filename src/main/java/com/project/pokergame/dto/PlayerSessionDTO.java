package com.project.pokergame.dto;

import lombok.Data;

@Data
public class PlayerSessionDTO {
    private Long id;
    private Long userAccountId;
    private Long gameSessionId;
    private Double stackSize;
    private Integer seatNumber;
    private Boolean isDealer;
}