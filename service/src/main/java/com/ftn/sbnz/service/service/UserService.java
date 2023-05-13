package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.model.User;
import com.ftn.sbnz.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserActive(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.isPresent();
    }

    public void getLoggedUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public boolean doesUserExist(String email, String username) {
        return userRepository.findByEmail(email).isPresent() || userRepository.findByUsername(username).isPresent();
    }

}
