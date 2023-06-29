package com.project.pokergame.controller;

import com.project.pokergame.dto.UserAccountDTO;
import com.project.pokergame.dto.UserRegisterDTO;
import com.project.pokergame.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private final UserAccountService userAccountService;

    public RegisterController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccountDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return ResponseEntity.ok(userAccountService.registerUser(userRegisterDTO));
    }
}
