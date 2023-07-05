package com.project.pokergame.validation.userProfile;

import org.springframework.stereotype.Component;

@Component
public class BioValidator {
    public void validate(String bio) {
        if (bio != null && bio.length() > 160) {
            throw new IllegalArgumentException("Bio must not exceed 160 characters.");
        }
    }
}
