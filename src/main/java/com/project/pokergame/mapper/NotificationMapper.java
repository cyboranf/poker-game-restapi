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
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);
}