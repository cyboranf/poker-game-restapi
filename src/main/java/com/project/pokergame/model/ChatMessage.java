package com.project.pokergame.model;

import com.project.pokergame.model.GameSession;
import com.project.pokergame.model.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Data
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_session_id", referencedColumnName = "id")
    private GameSession gameSession;

    @ManyToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    private String message;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}
