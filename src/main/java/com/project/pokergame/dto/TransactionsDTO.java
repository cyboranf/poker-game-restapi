package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionsDTO {
    private Long id;
    private Long userAccountId;
    private TransactionType transactionType;
    private Double amount;
    private LocalDateTime transactionDate;
    private String paymentMethod;
}