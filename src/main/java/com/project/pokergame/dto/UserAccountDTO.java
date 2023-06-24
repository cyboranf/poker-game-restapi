package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.AccountStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserAccountDTO {
    private Long id;
    private String username;
    private String email;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private Set<Long> roleIds;
}