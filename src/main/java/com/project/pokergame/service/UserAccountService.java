package com.project.pokergame.service;

import com.project.pokergame.dto.UserAccountDTO;
import com.project.pokergame.dto.register.UserImportantDTO;
import com.project.pokergame.mapper.UserAccountMapper;
import com.project.pokergame.model.Role;
import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.enumerated.AccountStatus;
import com.project.pokergame.repository.RoleRepository;
import com.project.pokergame.repository.UserAccountRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.project.pokergame.validation.EmailValidator;
import com.project.pokergame.validation.PasswordValidator;
import com.project.pokergame.validation.UsernameValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserAccountService(UserAccountRepository userAccountRepository, UserAccountMapper userAccountMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Optional<UserAccount> findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    /**
     * @param userImportantDTO
     * @return new userAccount
     * methods for register an accounts:
     */
    private UserAccount createUserAccount(UserImportantDTO userImportantDTO) {
        EmailValidator.validate(userImportantDTO.getEmail());
        UsernameValidator.validate(userImportantDTO.getUsername());
        PasswordValidator.validate(userImportantDTO.getPassword());

        if (userAccountRepository.existsByEmail(userImportantDTO.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userImportantDTO.getUsername());
        userAccount.setEmail(userImportantDTO.getEmail());
        userAccount.setPassword(passwordEncoder.encode(userImportantDTO.getPassword()));
        userAccount.setAccountStatus(AccountStatus.ACTIVE);
        Role role = roleRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        // Assigning the role to the user account
        userAccount.getRoles().add(role);

        // Saving the user account
        return userAccountRepository.save(userAccount);
    }

    public UserAccountDTO registerUser(UserImportantDTO userImportantDTO) {
        UserAccount savedUserAccount = createUserAccount(userImportantDTO);
        return userAccountMapper.toDTO(savedUserAccount);
    }

    /**
     * Methods to:
     * Changing data by Admin, mod, user
     * Changing status (suspended or active), by admin
     */

    // Method for Admin:
    public UserAccount changeAccountStatus(Long userId, AccountStatus status) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(userId);

        if (!userAccount.isPresent()) {
            throw new EntityNotFoundException("User not found with id = " + userId);
        }

        UserAccount user = userAccount.get();
        user.setAccountStatus(status);

        return userAccountRepository.save(user);
    }

    // Methods for all users:
    public UserAccount updateUserData(Long id, UserImportantDTO userAccountDTO) {
        EmailValidator.validate(userAccountDTO.getEmail());
        UsernameValidator.validate(userAccountDTO.getUsername());

        return userAccountRepository.findById(id).map(userAccount -> {
            userAccount.setUsername(userAccountDTO.getUsername());
            userAccount.setEmail(userAccountDTO.getEmail());

            return userAccountRepository.save(userAccount);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with id = " + id));
    }

    public UserAccount updateUserPassword(Long id , UserImportantDTO changedData){
        PasswordValidator.validate(changedData.getPassword());

        return userAccountRepository.findById(id).map(userAccount -> {
            userAccount.setPassword(passwordEncoder.encode(changedData.getPassword()));

            return userAccountRepository.save(userAccount);
        }).orElseThrow(()-> new EntityNotFoundException("User not found with id = "+ id));
    }
}
