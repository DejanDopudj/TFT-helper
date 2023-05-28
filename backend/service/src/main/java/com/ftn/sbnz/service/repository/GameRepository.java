package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


    List<Game> findByUsername(String username);
}
