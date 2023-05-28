package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


}
