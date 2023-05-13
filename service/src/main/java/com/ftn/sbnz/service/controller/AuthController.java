package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.JwtAuthenticationRequestDTO;
import com.ftn.sbnz.service.dto.creation.UserCreationDTO;
import com.ftn.sbnz.service.model.User;
import com.ftn.sbnz.service.security.UserTokenState;
import com.ftn.sbnz.service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> login(@Valid @RequestBody JwtAuthenticationRequestDTO authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        User user = authService.register(userCreationDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
