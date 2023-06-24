package com.project.pokergame.dto;

import com.project.pokergame.model.enumerated.Rank;
import com.project.pokergame.model.enumerated.Suit;
import lombok.Data;

@Data
public class CardDTO {
    private Long id;
    private Suit suit;
    private Rank rank;
    private String imageUrl;
}
