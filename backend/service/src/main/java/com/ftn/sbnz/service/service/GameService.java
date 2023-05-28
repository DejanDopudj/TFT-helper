package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.service.dto.game.AugmentConnectionDto;
import com.ftn.sbnz.service.dto.game.ChampionConnectionDto;
import com.ftn.sbnz.service.dto.game.GameAugmentsDto;
import com.ftn.sbnz.service.repository.*;
import org.kie.api.runtime.KieSession;
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
    @Autowired
    private ChampionRepository championRepository;
    @Autowired
    private ChampionLocationRepository championLocationRepository;
    @Autowired
    private AugmentLocationRepository augmentLocationRepository;
    @Autowired
    private KSessionService kSessionService;
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
        KieSession ksession = kSessionService.getKSessionAugmentConnection();
        Augment augment1 = augmentRepository.findById(augmentConnectionDto.getAugment1()).get();
        Augment augment2 = augmentRepository.findById(augmentConnectionDto.getAugment2()).get();
        Result result = new Result();
        ksession.insert(augment1);
        ksession.insert(augment2);
        ksession.insert(result);
        ksession.getAgenda().getAgendaGroup("areConnectedGroup").setFocus();
        long ruleFireCount = ksession.fireAllRules();
        String retValue = result.getValue();
        ksession.delete(ksession.getFactHandle(augment1));
        ksession.delete(ksession.getFactHandle(augment2));
        System.out.println(ruleFireCount);
        return retValue;
    }

    public String getChampionConnection(ChampionConnectionDto championConnectionDto) {
        KieSession ksession = kSessionService.getkSessionChampionConnection();
        Champion champion1 = championRepository.findById(championConnectionDto.getChampion1()).get();
        Champion champion2 = championRepository.findById(championConnectionDto.getChampion2()).get();
        Result result = new Result();
        ksession.insert(champion1);
        ksession.insert(champion2);
        ksession.insert(result);
        ksession.getAgenda().getAgendaGroup("areConnectedGroup2").setFocus();
        long ruleFireCount = ksession.fireAllRules();
        String retValue = result.getValue();
        ksession.delete(ksession.getFactHandle(champion1));
        ksession.delete(ksession.getFactHandle(champion2));
        ksession.delete(ksession.getFactHandle(result));
        System.out.println(ruleFireCount);
        return retValue;
    }

    public String getHistoryGrade(String username) {
        kSessionService.getKsessionHistoryGrade().setGlobal("username", username);
        Result result = new Result();
        kSessionService.getKsessionHistoryGrade().insert(result);
        long ruleFireCount = kSessionService.getKsessionHistoryGrade().fireAllRules();
        System.out.println(ruleFireCount);
        String retValue = result.getValue();
        return retValue;
    }

    public String getHoursPlayed(String username) {
        kSessionService.getKSessionHoursPlayed().setGlobal("username", username);
        Result result = new Result();
        kSessionService.getKSessionHoursPlayed().insert(result);
        long ruleFireCount = kSessionService.getKSessionHoursPlayed().fireAllRules();
        System.out.println(ruleFireCount);
        String retValue = result.getValue();
        return retValue;
    }



}
