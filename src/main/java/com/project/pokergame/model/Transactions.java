package com.project.pokergame.model;

import com.project.pokergame.model.enumerated.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    private Double amount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "payment_method")
    private String paymentMethod;


}