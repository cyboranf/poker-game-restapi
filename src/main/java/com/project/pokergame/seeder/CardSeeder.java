package com.project.pokergame.seeder;

import com.project.pokergame.model.Card;
import com.project.pokergame.model.enumerated.Rank;
import com.project.pokergame.model.enumerated.Suit;
import com.project.pokergame.repository.CardRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("seedCards")
public class CardSeeder {
    private final CardRepository cardRepository;

    public CardSeeder(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (cardRepository.count() == 0) {
            seedCards();
        }
    }

    private void seedCards() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card();
                card.setSuit(suit);
                card.setRank(rank);
                card.setImageUrl(generateImageUrl(suit, rank));
                cardRepository.save(card);
            }
        }
    }

    private String generateImageUrl(Suit suit, Rank rank) {
        String baseUrl = "/cards/";
        String imageName = rank.toString().toLowerCase() + "_" + suit.toString().toLowerCase() + ".png";
        return baseUrl + imageName;
    }
}
