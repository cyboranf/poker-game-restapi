package com.project.pokergame.seeder;

import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.UserProfile;
import com.project.pokergame.repository.UserAccountRepository;
import com.project.pokergame.repository.UserProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(3)
@Profile("seed")
public class UserProfileSeeder implements DatabaseSeeder {
    private final UserProfileRepository userProfileRepository;
    private final UserAccountRepository userAccountRepository;

    public UserProfileSeeder(UserProfileRepository userProfileRepository, UserAccountRepository userAccountRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        UserAccount basicUser = userAccountRepository.findByUsername("basicUser").get();
        UserAccount modUser = userAccountRepository.findByUsername("mod").get();
        UserAccount adminUser = userAccountRepository.findByUsername("admin").get();

        /**
         {
            "userAccount": "basicUser",
            "firstName": "John",
            "lastName": "User",
            "avatar": "/avatars/user.png",
            "country": "Poland",
            "bio": "Hello, I am John, I am a basic user!",
            "totalGamesPlayed": 0,
            "totalWinnings": 0.0
         }
         */

        UserProfile profile4BasicUser = new UserProfile();
        profile4BasicUser.setUserAccount(basicUser);
        profile4BasicUser.setFirstName("John");
        profile4BasicUser.setLastName("User");
        profile4BasicUser.setAvatar("/avatars/user.png");
        profile4BasicUser.setCountry("Poland");
        profile4BasicUser.setBio("Hello, I am John, I am a basic user!");
        profile4BasicUser.setTotalGamesPlayed(0);
        profile4BasicUser.setTotalWinnings(0.0);
        userProfileRepository.save(profile4BasicUser);

        /**
         {
            "userAccount": "modUser",
            "firstName": "Joe",
            "lastName": "Moderator",
            "avatar": "/avatars/moderator.png",
            "country": "Poland",
            "bio": "Hello, I am Joe, I am a moderator!",
            "totalGamesPlayed": 0,
            "totalWinnings": 0.0
         }
         */
        UserProfile profile4ModUser = new UserProfile();
        profile4ModUser.setUserAccount(modUser);
        profile4ModUser.setFirstName("Joe");
        profile4ModUser.setLastName("Moderator");
        profile4ModUser.setAvatar("/avatars/moderator.png");
        profile4ModUser.setCountry("Poland");
        profile4ModUser.setBio("Hello, I am Joe, I am a moderator!");
        profile4ModUser.setTotalGamesPlayed(0);
        profile4ModUser.setTotalWinnings(0.0);
        userProfileRepository.save(profile4ModUser);

        /**
         {
            "userAccount": "adminUser",
            "firstName": "Amelia",
            "lastName": "Admin",
            "avatar": "/avatars/admin.png",
            "country": "Poland",
            "bio": "Hello, I'm Amelia, I'm an admin! GL & HF",
            "totalGamesPlayed": 0,
            "totalWinnings": 0.0
         }
         */
        UserProfile profile4Admin = new UserProfile();
        profile4Admin.setUserAccount(adminUser);
        profile4Admin.setFirstName("Amelia");
        profile4Admin.setLastName("Admin");
        profile4Admin.setAvatar("/avatars/admin.png");
        profile4Admin.setCountry("Poland");
        profile4Admin.setBio("Hello, I'm Amelia, I'm an admin! GL & HF");
        profile4Admin.setTotalGamesPlayed(0);
        profile4Admin.setTotalWinnings(0.0);
        userProfileRepository.save(profile4Admin);
    }
}

