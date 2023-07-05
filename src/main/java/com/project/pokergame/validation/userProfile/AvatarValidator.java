package com.project.pokergame.validation.userProfile;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AvatarValidator {

    private static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*");

    public void validate(String avatar) {
        if (avatar == null || !URL_PATTERN.matcher(avatar).matches()) {
            throw new IllegalArgumentException("Avatar must be a valid URL.");
        }
    }
}
