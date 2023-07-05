package com.project.pokergame.validation.userAccount;

import com.project.pokergame.dto.register.UserImportantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountValidator {

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;

    @Autowired
    public UserAccountValidator(EmailValidator emailValidator,
                                PasswordValidator passwordValidator,
                                UsernameValidator usernameValidator) {
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.usernameValidator = usernameValidator;
    }

    public void validate(UserImportantDTO userImportantDTO) {
        emailValidator.validate(userImportantDTO.getEmail());
        usernameValidator.validate(userImportantDTO.getUsername());
        passwordValidator.validate(userImportantDTO.getPassword());
    }

    public void validatePassword(String password) {
        passwordValidator.validate(password);
    }

    public void validateEmailAndUsername(String email, String username) {
        emailValidator.validate(email);
        usernameValidator.validate(username);
    }
}
