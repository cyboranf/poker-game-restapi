package com.project.pokergame.mapper;

import com.project.pokergame.dto.PlayerSessionDTO;
import com.project.pokergame.model.PlayerSession;
import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.GameSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerSessionMapper {
    PlayerSessionMapper INSTANCE = Mappers.getMapper(PlayerSessionMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    @Mapping(source = "gameSession.id", target = "gameSessionId")
    PlayerSessionDTO toDTO(PlayerSession playerSession);

    @Mapping(target = "userAccount", ignore = true)
    @Mapping(target = "gameSession", ignore = true)
    PlayerSession DTO2PlayerSession(PlayerSessionDTO playerSessionDTO);

    default UserAccount mapUserAccount(Long value) {
        if (value == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(value);
        return userAccount;
    }

    default GameSession mapGameSession(Long value) {
        if (value == null) {
            return null;
        }
        GameSession gameSession = new GameSession();
        gameSession.setId(value);
        return gameSession;
    }
}
