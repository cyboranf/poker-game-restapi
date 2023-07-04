package com.project.pokergame.seeder;

import com.project.pokergame.model.Role;
import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.enumerated.AccountStatus;
import com.project.pokergame.repository.RoleRepository;
import com.project.pokergame.repository.UserAccountRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Component
@Order(2)
@Profile("seed")
public class UserAccountSeeder implements DatabaseSeeder {

    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountSeeder(UserAccountRepository userAccountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Role userRole = roleRepository.findById(1)
                .orElseThrow(()->new EntityNotFoundException("Role with id = 1 not found"));
        Role modRole = roleRepository.findById(2)
                .orElseThrow(()->new EntityNotFoundException("Role with id = 2 not found"));
        Role adminRole = roleRepository.findById(3)
                .orElseThrow(()->new EntityNotFoundException("Role with id = 3 not found"));

        // Create basic user
        /**
        {
            "username": "basicUser",
            "password": "basicUser",
            "email": "basicUser@gmail.com",
            "accountStatus": "ACTIVE",
            "roles": ["USER"]
        }
        */
        UserAccount user = new UserAccount();
        user.setUsername("basicUser");
        user.setPassword(passwordEncoder.encode("basicUser"));
        user.setEmail("basicUser@gmail.com");
        user.setAccountStatus(AccountStatus.ACTIVE);
        Set<Role> set4User = new HashSet<>();
        set4User.add(userRole);
        user.setRoles(set4User);

        userAccountRepository.save(user);

        // Create mod account
        /**
         {
            "username": "mod",
            "password": "moderator",
            "email": "mod@gmail.com",
            "accountStatus": "ACTIVE",
            "roles": ["MOD"]
         }
         */
        UserAccount moderator = new UserAccount();
        moderator.setUsername("mod");
        moderator.setPassword(passwordEncoder.encode("moderator"));
        moderator.setEmail("mod@gmail.com");
        moderator.setAccountStatus(AccountStatus.ACTIVE);
        Set<Role> set4Mod = new HashSet<>();
        set4Mod.add(modRole);
        moderator.setRoles(set4Mod);

        userAccountRepository.save(moderator);

        // Create admin account
        /**
         {
            "username": "admin",
            "password": "admin",
            "email": "admin@gmail.com",
            "accountStatus": "ACTIVE",
            "roles": ["ADMIN"]
         }
         */
        UserAccount admin = new UserAccount();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@gmail.com");
        admin.setAccountStatus(AccountStatus.ACTIVE);
        Set<Role> set4Admin = new HashSet<>();
        set4Admin.add(adminRole);
        admin.setRoles(set4Admin);

        userAccountRepository.save(admin);
    }
}
