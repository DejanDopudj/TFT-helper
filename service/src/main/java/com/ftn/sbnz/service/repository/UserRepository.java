package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserRepository> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
