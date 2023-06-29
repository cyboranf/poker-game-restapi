package com.project.pokergame.service;

import com.project.pokergame.dto.UserAccountDTO;
import com.project.pokergame.dto.UserRegisterDTO;
import com.project.pokergame.mapper.UserAccountMapper;
import com.project.pokergame.model.Role;
import com.project.pokergame.model.UserAccount;
import com.project.pokergame.model.enumerated.AccountStatus;
import com.project.pokergame.repository.RoleRepository;
import com.project.pokergame.repository.UserAccountRepository;

import javax.transaction.Transactional;

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

    /**
     * @param userRegisterDTO
     * @return new userAccount
     * methods for register an accounts:
     */
    private UserAccount createUserAccountFromRegisterDTO(UserRegisterDTO userRegisterDTO) {
        if (userAccountRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userRegisterDTO.getUsername());
        userAccount.setEmail(userRegisterDTO.getEmail());
        userAccount.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userAccount.setAccountStatus(AccountStatus.ACTIVE);
        Role role = roleRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        // Assigning the role to the user account
        userAccount.getRoles().add(role);

        // Saving the user account
        return userAccountRepository.save(userAccount);
    }

    public UserAccountDTO registerUser(UserRegisterDTO userRegisterDTO) {
        UserAccount savedUserAccount = createUserAccountFromRegisterDTO(userRegisterDTO);
        return userAccountMapper.toDTO(savedUserAccount);
    }

    public Optional<UserAccount> findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

}
