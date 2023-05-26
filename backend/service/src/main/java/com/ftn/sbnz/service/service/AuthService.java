package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.dto.JwtAuthenticationRequestDTO;
import com.ftn.sbnz.service.dto.creation.UserCreationDTO;
import com.ftn.sbnz.service.exception.UserAlreadyExistsException;
import com.ftn.sbnz.model.User;
import com.ftn.sbnz.service.repository.UserRepository;
import com.ftn.sbnz.service.security.UserTokenState;
import com.ftn.sbnz.service.util.TokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    public ResponseEntity<UserTokenState> login(JwtAuthenticationRequestDTO authRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            if(!userService.isUserActive(user.getUsername())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String jwt = tokenUtils.generateToken(user.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();
            userService.getLoggedUser();
            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public User register(UserCreationDTO dto) {
        if(!userService.doesUserExist(dto.getEmail(), dto.getUsername())){
            User user = modelMapper.map(dto, User.class);
            user.setId(UUID.randomUUID());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setRoles(roleService.findByName("ROLE_USER"));
            userRepository.save(user);
            return user;
        }
        else {
            throw new UserAlreadyExistsException();
        }
    }
}
