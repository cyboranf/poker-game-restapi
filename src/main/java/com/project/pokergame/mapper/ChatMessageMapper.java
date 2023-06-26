package com.project.pokergame.mapper;

import com.project.pokergame.dto.ChatMessageDTO;
import com.project.pokergame.model.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMessageMapper {
    ChatMessageMapper INSTANCE = Mappers.getMapper(ChatMessageMapper.class);

    @Mapping(source = "gameSession.id", target = "gameSessionId")
    @Mapping(source = "userAccount.id", target = "userAccountId")
    ChatMessageDTO toDTO(ChatMessage chatMessage);

    @Mapping(target = "gameSession", expression = "java(new com.project.pokergame.model.GameSession())")
    @Mapping(target = "gameSession.id", source = "gameSessionId")
    @Mapping(target = "userAccount", expression = "java(new com.project.pokergame.model.UserAccount())")
    @Mapping(target = "userAccount.id", source = "userAccountId")
    ChatMessage DTO2ChatMessage(ChatMessageDTO chatMessageDTO);
}
