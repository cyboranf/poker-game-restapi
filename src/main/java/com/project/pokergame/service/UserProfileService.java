package com.project.pokergame.service;

import com.project.pokergame.dto.UserProfileDTO;
import com.project.pokergame.mapper.UserProfileMapper;
import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.repository.UserAccountRepository;
import com.project.pokergame.repository.UserProfileRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.project.pokergame.validation.AvatarValidator;
import com.project.pokergame.validation.BioValidator;
import com.project.pokergame.validation.CountryValidator;
import com.project.pokergame.validation.NameValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileService(UserProfileRepository userProfileRepository, UserAccountRepository userAccountRepository, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userAccountRepository = userAccountRepository;
        this.userProfileMapper = userProfileMapper;
    }

    /**
     * Method to add more info about player(user)
     */
    public UserProfileDTO addUserProfile(Long userAccountId, UserProfileDTO userProfileDTO) {
        NameValidator.validate(userProfileDTO.getFirstName());
        NameValidator.validate(userProfileDTO.getLastName());
        AvatarValidator.validate(userProfileDTO.getAvatar());
        CountryValidator.validate(userProfileDTO.getCountry());
        BioValidator.validate(userProfileDTO.getBio());

        UserAccount userAccount = userAccountRepository.findById(userAccountId)
                .orElseThrow(() -> new IllegalArgumentException("UserAccount not found with id = " + userAccountId));

        UserProfile userProfile = userProfileMapper.DTO2UserProfile(userProfileDTO);

        userProfile.setUserAccount(userAccount);

        UserProfile savedUserProfile = userProfileRepository.save(userProfile);

        // Convert saved UserProfile entity back to DTO and return
        return userProfileMapper.toDTO(savedUserProfile);
    }

    /**
     * Method to edit user profile
     */
    public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO userProfileDTO) {
        NameValidator.validate(userProfileDTO.getFirstName());
        NameValidator.validate(userProfileDTO.getLastName());
        AvatarValidator.validate(userProfileDTO.getAvatar());
        CountryValidator.validate(userProfileDTO.getCountry());
        BioValidator.validate(userProfileDTO.getBio());

        UserProfile editedUser = userProfileRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User profile not found with id = " + userId));

        editedUser.setFirstName(userProfileDTO.getFirstName());
        editedUser.setLastName(userProfileDTO.getLastName());
        editedUser.setAvatar(userProfileDTO.getAvatar());
        editedUser.setCountry(userProfileDTO.getCountry());
        editedUser.setBio(userProfileDTO.getBio());

        UserProfile savedUser = userProfileRepository.save(editedUser);

        return userProfileMapper.toDTO(savedUser);
    }

    /**
     * Methods to get user profile / users profiles
     */
    public UserProfileDTO getUserProfile(Long userID) {
        UserProfile user = userProfileRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User profile not found with id = " + userID));

        return userProfileMapper.toDTO(user);
    }

    public List<UserProfileDTO> viewUsersProfileByTotalWinnings() {
        List<UserProfile> allUsers = userProfileRepository.findAll();

        allUsers.sort((user1, user2) -> user2.getTotalWinnings().compareTo(user1.getTotalWinnings()));

        return allUsers.stream().map(userProfileMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserProfileDTO> viewUsersProfileByTotalGamesPlayed() {
        List<UserProfile> allUsers = userProfileRepository.findAll();

        allUsers.sort((user1, user2) -> user2.getTotalGamesPlayed().compareTo(user1.getTotalGamesPlayed()));

        return allUsers.stream().map(userProfileMapper::toDTO)
                .collect(Collectors.toList());
    }
}
