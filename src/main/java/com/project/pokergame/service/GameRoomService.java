package com.project.pokergame.service;

import com.project.pokergame.dto.GameRoomDTO;
import com.project.pokergame.exception.RoomClosedException;
import com.project.pokergame.exception.RoomFullException;
import com.project.pokergame.exception.UserAlreadyInRoomException;
import com.project.pokergame.mapper.GameRoomMapper;
import com.project.pokergame.model.GameRoom;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.model.enumerated.Role;
import com.project.pokergame.model.enumerated.RoomStatus;
import com.project.pokergame.repository.GameRoomRepository;
import com.project.pokergame.repository.UserProfileRepository;
import com.project.pokergame.validation.gameRoom.BigBlindValidator;
import com.project.pokergame.validation.gameRoom.RoomMaxPlayersValidator;
import com.project.pokergame.validation.gameRoom.RoomNameValidator;
import com.project.pokergame.validation.gameRoom.SmallBlindValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameRoomService {
    private final GameRoomRepository gameRoomRepository;
    private final UserProfileRepository userProfileRepository;
    private final GameRoomMapper gameRoomMapper;

    public GameRoomService(GameRoomRepository gameRoomRepository, UserProfileRepository userProfileRepository, GameRoomMapper roomMapper) {
        this.gameRoomRepository = gameRoomRepository;
        this.userProfileRepository = userProfileRepository;
        this.gameRoomMapper = roomMapper;
    }

    /**
     * Method to:
     * Create Game Room
     */
    public GameRoomDTO createGameRoom(GameRoomDTO gameRoomDTO, Long userId) {
        RoomNameValidator.validate(gameRoomDTO.getName());
        RoomMaxPlayersValidator.validate(gameRoomDTO.getMaxPlayers());
        SmallBlindValidator.validate(gameRoomDTO.getSmallBlind());
        BigBlindValidator.validate(gameRoomDTO.getBigBlind(), gameRoomDTO.getSmallBlind());

        GameRoom gameRoom = new GameRoom();
        gameRoom.setName(gameRoomDTO.getName());
        gameRoom.setMaxPlayers(gameRoomDTO.getMaxPlayers());
        gameRoom.setSmallBlind(gameRoomDTO.getSmallBlind());
        gameRoom.setBigBlind(gameRoomDTO.getBigBlind());
        gameRoom.setCreatedAt(LocalDateTime.now());
        gameRoom.setStatus(gameRoomDTO.getStatus());
        UserProfile creator = userProfileRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id = " + userId));
        gameRoom.setCreator(creator);

        GameRoom savedGameRoom = gameRoomRepository.save(gameRoom);
        return gameRoomMapper.toDTO(savedGameRoom);
    }

    /**
     * Method to:
     * Join to a Game Room
     */
    public GameRoomDTO joinGameRoom(Long userId, Long roomId) {
        GameRoom roomToJoin = gameRoomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Can not found room with id = " + roomId));
        UserProfile joiner = userProfileRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id = " + userId));
        if (roomToJoin.getStatus().equals(RoomStatus.CLOSED)) {
            throw new RoomClosedException("Cannot join a closed room.");
        }
        if (roomToJoin.getPlayers().size() >= roomToJoin.getMaxPlayers()) {
            throw new RoomFullException("The room has reached its maximum capacity.");
        }
        boolean userAlreadyInRoom = roomToJoin.getPlayers().stream().anyMatch(player -> player.getId().equals(joiner.getId()));
        if (userAlreadyInRoom) {
            throw new UserAlreadyInRoomException("User is already in the game room.");
        }

        List<UserProfile> players = roomToJoin.getPlayers();
        players.add(joiner);
        roomToJoin.setPlayers(players);
        gameRoomRepository.save(roomToJoin);

        return gameRoomMapper.toDTO(roomToJoin);
    }

    /**
     * Method to:
     * Close a Game Room by creator or mod or admin
     */
    public GameRoomDTO closeGameRoom(Long userId, Long roomId) {
        GameRoom roomToClose = gameRoomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Can not found room with id = " + roomId));
        UserProfile closer = userProfileRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Can not found user with id = " + userId));

        boolean isCloserAdminOrMod = closer.getUserAccount().getRoles().stream()
                .anyMatch(role -> role.getName().equals(Role.ADMIN) || role.getName().equals(Role.MOD));
        boolean isCloserTheCreator = roomToClose.getCreator().getId().equals(closer.getId());

        if (!isCloserAdminOrMod && !isCloserTheCreator) {
            throw new RoomClosedException("Cannot close the room. Only ADMIN, MOD, or the creator can do this.");
        }
        roomToClose.setStatus(RoomStatus.CLOSED);
        GameRoom savedRoom = gameRoomRepository.save(roomToClose);
        return gameRoomMapper.toDTO(savedRoom);
    }

    /**
     * Method to:
     * Get a Game Rooms which are ACTIVE
     */
    public List<GameRoomDTO> getActiveGameRooms() {
        List<GameRoom> allRooms = gameRoomRepository.findAll();
        return allRooms.stream()
                .filter(r -> r.getStatus().equals(RoomStatus.ACTIVE))
                .map(gameRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method to:
     * Get a Game Rooms which are ACTIVE
     * sorted by free places
     */
    public List<GameRoomDTO> getActiveRoomsSortedByFreePlaces() {
        List<GameRoom> allRooms = gameRoomRepository.findAll();
        return allRooms.stream()
                .filter(r -> r.getStatus().equals(RoomStatus.ACTIVE))
                .sorted(Comparator.comparing(r -> r.getMaxPlayers() - r.getPlayers().size()))
                .map(gameRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method to:
     * Get a Game Room by roomId
     */
    public GameRoomDTO getGameRoomById(Long roomId) {
        GameRoom searchingRoom = gameRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Can not found Game room with id = " + roomId));
        return gameRoomMapper.toDTO(searchingRoom);
    }
}
