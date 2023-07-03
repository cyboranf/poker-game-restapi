package com.project.pokergame.validation;

import java.util.regex.Pattern;

public class AvatarValidator {

    private static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*");

    public static void validate(String avatar) {
        if (avatar == null || !URL_PATTERN.matcher(avatar).matches()) {
            throw new IllegalArgumentException("Avatar must be a valid URL.");
        }
    }
}
