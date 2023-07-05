package com.project.pokergame.seeder;

import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.model.enumerated.RoomStatus;
import com.project.pokergame.repository.GameRoomRepository;
import com.project.pokergame.repository.UserProfileRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Order(4)
@Component
@Profile("seed")
public class GameRoomSeeder implements DatabaseSeeder {
    private final GameRoomRepository gameRoomRepository;
    private final UserProfileRepository userProfileRepository;

    public GameRoomSeeder(GameRoomRepository gameRoomRepository, UserProfileRepository userProfileRepository) {
        this.gameRoomRepository = gameRoomRepository;
        this.userProfileRepository = userProfileRepository;
    }

    /**
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Create gameRoom by basicUser to check:
        // that creator, mod and admin can close room
        /**
         {
         "name": "Chip 'n' Chuckles Poker Parlor",
         "maxPlayers": "10",
         "SmallBlind": "100.0",
         "BigBlind": "200.0",
         "CreatedAt": "LocalDateTime.now"
         "Creator": "John"
         "players": []
         }
         */
        GameRoom gameRoom = new GameRoom();
        gameRoom.setName("Chip 'n' Chuckles Poker Parlor");
        gameRoom.setMaxPlayers(10);
        gameRoom.setSmallBlind(100.0);
        gameRoom.setBigBlind(200.0);
        gameRoom.setCreatedAt(LocalDateTime.now());
        gameRoom.setStatus(RoomStatus.ACTIVE);
        UserProfile creator = userProfileRepository.findByFirstName("John")
                .orElseThrow(() -> new EntityNotFoundException("User Profile not found with first name = John"));
        gameRoom.setCreator(creator);
        List<UserProfile> players = new ArrayList<>();
        gameRoom.setPlayers(players);

        gameRoomRepository.save(gameRoom);
    }
}
