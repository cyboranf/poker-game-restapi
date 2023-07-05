package com.project.pokergame.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameSessionDTO {
    private Long id;
    private Long gameRoomId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Double potSize;
    private Double lastCall;
}
