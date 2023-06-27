package com.project.pokergame.model;

import com.project.pokergame.model.enumerated.Rank;
import com.project.pokergame.model.enumerated.Suit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Suit suit;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(name = "image_url")
    private String imageUrl;
}
