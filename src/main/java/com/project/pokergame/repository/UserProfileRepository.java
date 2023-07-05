package com.project.pokergame.repository;

import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByFirstName(String firstName);
}
