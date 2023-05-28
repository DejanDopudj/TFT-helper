package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.Augment;
import com.ftn.sbnz.model.Component;
import com.ftn.sbnz.model.Game;
import com.ftn.sbnz.service.dto.game.GameAugmentsDto;
import com.ftn.sbnz.service.repository.AugmentRepository;
import com.ftn.sbnz.service.repository.ComponentRepository;
import com.ftn.sbnz.service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ComponentRepository componentRepository;
    @Autowired
    private AugmentRepository augmentRepository;
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


    public boolean addComponent(Long id, String componentName) {
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            Game game = optGame.get();
            Component component = componentRepository.findById(componentName).get();
            game.addComponent(component);
            game.setGold(15);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    public Boolean addAugments(GameAugmentsDto gameAugmentsDto) {
        Optional<Game> optGame = gameRepository.findById(gameAugmentsDto.getId());
        if(optGame.isPresent()){
            gameAugmentsDto.getAugmentNames().stream().forEach(
                    augmentName -> {
                        Augment augment = augmentRepository.findById(augmentName).get();
                        optGame.get().addAugmentChoice(augment);
                    }
            );
            gameRepository.save(optGame.get());
        }
        return false;

    }
}
