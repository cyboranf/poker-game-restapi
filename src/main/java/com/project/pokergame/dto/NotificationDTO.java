package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.NotificationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long id;
    private Long userAccountId;
    private String content;
    private LocalDateTime createdAt;
    private NotificationStatus status;
}
