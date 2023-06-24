package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.ActionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameActionDTO {
    private Long id;
    private Long gameSessionId;
    private Long playerSessionId;
    private ActionType type;
    private Double amount;
    private LocalDateTime actionTime;
}
