package com.project.pokergame.validation.userProfile;

public class BioValidator {
    public static void validate(String bio) {
        if (bio != null && bio.length() > 160) {
            throw new IllegalArgumentException("Bio must not exceed 160 characters.");
        }
    }
}
