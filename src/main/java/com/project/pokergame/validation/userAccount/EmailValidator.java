package com.project.pokergame.validation.userAccount;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    /**
     * Email pattern explained:
     * ^ - start of the line
     * [\w\.-]+ - one or more word characters, dots or hyphens
     *
     * @ - at sign
     * [\w\.-]+ - one or more word characters, dots or hyphens
     * \. - dot
     * [a-zA-Z]{2,} - two or more letter characters
     * $ - end of the line
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");

    public void validate(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
}
