package com.project.pokergame.mapper;

import com.project.pokergame.dto.NotificationDTO;
import com.project.pokergame.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    NotificationDTO toDTO(Notification notification);

    @Mapping(target = "userAccount", ignore = true)
    Notification DTO2Notification(NotificationDTO notificationDTO);
}
