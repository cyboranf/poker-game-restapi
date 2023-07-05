package com.project.pokergame.validation.gameRoom;

import com.project.pokergame.exception.gameRoom.RoomClosedException;
import com.project.pokergame.exception.gameRoom.RoomFullException;
import com.project.pokergame.exception.gameRoom.UserAlreadyInRoomException;
import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.model.enumerated.RoomStatus;
import org.springframework.stereotype.Component;

@Component
public class PlayerJoinValidator {

    public void validate(GameRoom gameRoom, UserProfile userProfile) {
        if (gameRoom.getStatus().equals(RoomStatus.CLOSED)) {
            throw new RoomClosedException("Cannot join a closed room.");
        }

        if (gameRoom.getPlayers().size() >= gameRoom.getMaxPlayers()) {
            throw new RoomFullException("The room has reached its maximum capacity.");
        }

        boolean userAlreadyInRoom = gameRoom.getPlayers().stream().anyMatch(player -> player.getId().equals(userProfile.getId()));

        if (userAlreadyInRoom) {
            throw new UserAlreadyInRoomException("User is already in the game room.");
        }
    }
}
