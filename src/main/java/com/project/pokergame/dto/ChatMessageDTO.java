package com.project.pokergame.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDTO {
    private Long id;
    private Long gameSessionId;
    private Long userAccountId;
    private String message;
    private LocalDateTime sentAt;
}
