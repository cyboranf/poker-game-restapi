package com.project.pokergame.mapper;

import com.project.pokergame.dto.CardDTO;
import com.project.pokergame.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDTO cardToCardDTO(Card card);

    Card cardDTOToCard(CardDTO cardDTO);
}
