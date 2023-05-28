package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.service.dto.game.AugmentConnectionDto;
import com.ftn.sbnz.service.dto.game.ChampionConnectionDto;
import com.ftn.sbnz.service.dto.game.GameAugmentsDto;
import com.ftn.sbnz.service.repository.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ComponentRepository componentRepository;
    @Autowired
    private AugmentRepository augmentRepository;
    @Autowired
    private ChampionRepository championRepository;
    @Autowired
    private ChampionLocationRepository championLocationRepository;
    @Autowired
    private AugmentLocationRepository augmentLocationRepository;
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


    public String getAugmentConnection(AugmentConnectionDto augmentConnectionDto) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("backwardKsession");
        Augment augment1 = augmentRepository.findById(augmentConnectionDto.getAugment1()).get();
        Augment augment2 = augmentRepository.findById(augmentConnectionDto.getAugment2()).get();
        ksession.insert(augment1);
        ksession.insert(augment2);
        List<AugmentLocation> augmentLocations = augmentLocationRepository.findAll();
        for(AugmentLocation augmentLocation : augmentLocations){
            ksession.insert(augmentLocation);
        }
        ksession.getAgenda().getAgendaGroup("areConnectedGroup").setFocus();
        long ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
        return "true";
    }

    public String getChampionConnection(ChampionConnectionDto championConnectionDto) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("backwardKsession2");
        Champion champion1 = championRepository.findById(championConnectionDto.getChampion1()).get();
        Champion champion2 = championRepository.findById(championConnectionDto.getChampion2()).get();
        ksession.insert(champion1);
        ksession.insert(champion2);
        List<ChampionLocation> championLocations = championLocationRepository.findAll();
        for(ChampionLocation championLocation : championLocations){
            ksession.insert(championLocation);
        }
        ksession.getAgenda().getAgendaGroup("areConnectedGroup2").setFocus();
        long ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
        return "true";
    }
}
