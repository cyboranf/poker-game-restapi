package com.project.pokergame.validation.userAccount;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
@Component
public class UsernameValidator {
    /**
     * Username pattern explained:
     * ^ - start of the line
     * [A-Za-z0-9_-] - matches uppercase letters, lowercase letters, digits, underscores, and hyphens
     * {3,15} - between 3 to 15 characters
     * $ - end of the line
     */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_-]{3,15}$");

    public void validate(String username) {
        if (username == null || !USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException("Username must be between 3 and 15 characters and can only contain letters, numbers, hyphens, and underscores.");
        }
    }
}
