package com.project.pokergame.validation.userProfile;

import com.project.pokergame.model.UserProfile;
import com.project.pokergame.validation.userAccount.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileValidator {
    private final AvatarValidator avatarValidator;
    private final BioValidator bioValidator;
    private final CountryValidator countryValidator;
    private final NameValidator nameValidator;

    @Autowired
    public UserProfileValidator(AvatarValidator avatarValidator,
                                BioValidator bioValidator,
                                CountryValidator countryValidator,
                                NameValidator nameValidator) {
        this.avatarValidator = avatarValidator;
        this.bioValidator = bioValidator;
        this.countryValidator = countryValidator;
        this.nameValidator = nameValidator;
    }

    public void validate(UserProfile userProfile) {
        nameValidator.validate(userProfile.getFirstName());
        nameValidator.validate(userProfile.getLastName());
        avatarValidator.validate(userProfile.getAvatar());
        bioValidator.validate(userProfile.getBio());
        countryValidator.validate(userProfile.getCountry());
    }
}
