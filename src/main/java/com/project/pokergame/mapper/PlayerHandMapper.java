package com.project.pokergame.mapper;

import com.project.pokergame.dto.PlayerHandDTO;
import com.project.pokergame.model.PlayerHand;
import com.project.pokergame.model.PlayerSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CardMapper.class})
public interface PlayerHandMapper {
    PlayerHandMapper INSTANCE = Mappers.getMapper(PlayerHandMapper.class);

    @Mapping(source = "playerSession.id", target = "playerSessionId")
    @Mapping(source = "card1", target = "card1")
    @Mapping(source = "card2", target = "card2")
    PlayerHandDTO toDTO(PlayerHand playerHand);

    @Mapping(target = "playerSession", ignore = true)
    @Mapping(target = "card1", ignore = true)
    @Mapping(target = "card2", ignore = true)
    PlayerHand DTO2PlayerHand(PlayerHandDTO playerHandDTO);

    default PlayerSession map(Long value) {
        if (value == null) {
            return null;
        }
        PlayerSession playerSession = new PlayerSession();
        playerSession.setId(value);
        return playerSession;
    }
}
