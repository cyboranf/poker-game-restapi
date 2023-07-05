package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.RoomStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameRoomDTO {
    private Long id;
    private String name;
    private Integer maxPlayers;
    private Double smallBlind;
    private Double bigBlind;
    private RoomStatus status;

}
