package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.Game;
import com.ftn.sbnz.service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    public boolean increaseLevel(Long id){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setLevel(optGame.get().getLevel() + 1);
            gameRepository.save(optGame.get());
            return true;
        }
        return false;
    }
    public boolean decreaseLevel(Long id){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setLevel(optGame.get().getLevel() - 1);
            gameRepository.save(optGame.get());
            return true;
        }
        return false;
    }

    public boolean setGold(Long id, int gold){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setGold(gold);
            gameRepository.save(optGame.get());
            return true;
        }
        return false;
    }
    public boolean setRound(Long id, int round){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setRound(round);
            gameRepository.save(optGame.get());
            return true;
        }
        return false;
    }
    public boolean setHP(Long id, int hp){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setHp(hp);
            gameRepository.save(optGame.get());
            return true;
        }
        return false;
    }
    public boolean setPlace(Long id, int place){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setPlace(place);
            gameRepository.save(optGame.get());
            return true;
        }
        return false;
    }




}
