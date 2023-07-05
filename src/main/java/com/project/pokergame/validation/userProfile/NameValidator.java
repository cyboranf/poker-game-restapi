package com.project.pokergame.validation.userProfile;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class NameValidator {
    /**
     *     Name pattern explained:
     *  Only alphabets, 1 to 50 characters
     */
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,50}$");

    public void validate(String name) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()){
            throw new IllegalArgumentException("Name must be 2 to 50 character long and only contains alphabet.");
        }
    }
}
