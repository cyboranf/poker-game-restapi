package com.project.pokergame.mapper;

import com.project.pokergame.dto.PlayerHandDTO;
import com.project.pokergame.model.PlayerHand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CardMapper.class)
public interface PlayerHandMapper {
    PlayerHandMapper INSTANCE = Mappers.getMapper(PlayerHandMapper.class);

    @Mapping(source = "playerSession.id", target = "playerSessionId")
    @Mapping(source = "card1", target = "card1")
    @Mapping(source = "card2", target = "card2")
    PlayerHandDTO playerHandToPlayerHandDTO(PlayerHand playerHand);

    @Mapping(source = "playerSessionId", target = "playerSession.id")
    @Mapping(source = "card1", target = "card1")
    @Mapping(source = "card2", target = "card2")
    PlayerHand playerHandDTOToPlayerHand(PlayerHandDTO playerHandDTO);
}
