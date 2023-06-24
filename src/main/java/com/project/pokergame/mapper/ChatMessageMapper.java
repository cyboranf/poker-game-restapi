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
    ChatMessageDTO chatMessageToChatMessageDTO(ChatMessage chatMessage);

    @Mapping(target = "gameSession", ignore = true)
    @Mapping(target = "userAccount", ignore = true)
    ChatMessage chatMessageDTOToChatMessage(ChatMessageDTO chatMessageDTO);
}
