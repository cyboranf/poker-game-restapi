package com.project.pokergame.validation;

import java.util.regex.Pattern;

public class PasswordValidator {
    /**
     *     Password pattern explained:
     *  ^ - start of the line
     *  (?=.*[0-9]) - at least one digit
     *  (?=.*[a-z]) - at least one lowercase letter
     *  (?=.*[A-Z]) - at least one uppercase letter
     *  .{8,} - at least 8 characters
     *  $ - end of the line
     */
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    public static void validate(String password) {
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, and one uppercase letter.");
        }
    }
}
