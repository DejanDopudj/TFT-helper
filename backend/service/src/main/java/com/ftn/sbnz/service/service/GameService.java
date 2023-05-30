package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.event.AugmentEvent;
import com.ftn.sbnz.model.event.RoundResultEvent;
import com.ftn.sbnz.model.event.TurnStartEvent;
import com.ftn.sbnz.service.dto.game.*;
import com.ftn.sbnz.service.repository.*;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private CompositionRepository compositionRepository;
    @Autowired
    private KSessionService kSessionService;

    private HashMap<Long, Game> positionSessionGameMap = new HashMap<>();

    public boolean increaseLevel(Long id){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            Game game = optGame.get();
            game.setLevel(game.getLevel() + 1);
            game.getPlayer().setLevel(game.getPlayer().getLevel() + 1);
            gameRepository.save(game);
            Game inMemoryGame = positionSessionGameMap.get(game.getId());
            inMemoryGame.setLevel(inMemoryGame.getLevel() + 1);
            inMemoryGame.getPlayer().setLevel(inMemoryGame.getPlayer().getLevel() + 1);
            return true;
        }
        return false;
    }
    public boolean decreaseLevel(Long id){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            Game game = optGame.get();
            game.setLevel(game.getLevel() - 1);
            game.getPlayer().setLevel(game.getPlayer().getLevel() - 1);
            gameRepository.save(game);
            Game inMemoryGame = positionSessionGameMap.get(game.getId());
            inMemoryGame.setLevel(inMemoryGame.getLevel() - 1);
            inMemoryGame.getPlayer().setLevel(inMemoryGame.getPlayer().getLevel() - 1);
            return true;
        }
        return false;
    }

    public boolean setGold(Long id, int gold){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            Game game = optGame.get();
            game.setGold(gold);
            game.getPlayer().setGold(gold);
            gameRepository.save(game);
            Game inMemoryGame = positionSessionGameMap.get(game.getId());
            inMemoryGame.setGold(gold);
            inMemoryGame.getPlayer().setGold(gold);
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
            Game game = optGame.get();
            game.setHp(hp);
            game.getPlayer().setHp(hp);
            gameRepository.save(game);
            Game inMemoryGame = positionSessionGameMap.get(game.getId());
            inMemoryGame.setHp(hp);
            inMemoryGame.getPlayer().setHp(hp);
            return true;
        }
        return false;
    }
    public boolean setPlace(Long id, int place){
        Optional<Game> optGame = gameRepository.findById(id);
        if(optGame.isPresent()){
            optGame.get().setPlace(place);
            gameRepository.save(optGame.get());
            KieSession positionSession = kSessionService.getPositionSession(optGame.get().getUsername());
            positionSession.delete(positionSession.getFactHandle(positionSessionGameMap.get(optGame.get().getId())));
            kSessionService.resetCompositionSession(optGame.get().getUsername());
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
            gameRepository.save(game);
            KieSession positionSession = kSessionService.getPositionSession(optGame.get().getUsername());
            positionSession.delete(positionSession.getFactHandle(positionSessionGameMap.get(optGame.get().getId())));
            positionSession.insert(optGame.get());
            positionSessionGameMap.put(optGame.get().getId(), optGame.get());
            return true;
        }
        return false;
    }

    public Boolean addAugments(GameAugmentsDto gameAugmentsDto) {
        Optional<Game> optGame = gameRepository.findById(gameAugmentsDto.getId());
        if(optGame.isPresent()){
            optGame.get().setAugmentChoice(new ArrayList<>());
            gameAugmentsDto.getAugmentNames().stream().forEach(
                    augmentName -> {
                        Augment augment = augmentRepository.findById(augmentName).get();
                        optGame.get().addAugmentChoice(augment);
                    }
            );
        }
        KieSession ksession = kSessionService.getCompositionSession("test");
        optGame.get().setPhase(0);
        ksession.insert(optGame.get());
        List<Composition> compositions = compositionRepository.findAll();
        for(Composition composition : compositions){
            optGame.get().getCompValue().put(composition, 0.0);
        }
        ksession.fireAllRules();
        ksession.delete(ksession.getFactHandle(optGame.get()));
        gameRepository.save(optGame.get());
        KieSession positionSession = kSessionService.getPositionSession(optGame.get().getUsername());
        positionSession.delete(positionSession.getFactHandle(positionSessionGameMap.get(optGame.get().getId())));
        positionSession.insert(optGame.get());
        positionSessionGameMap.put(optGame.get().getId(), optGame.get());
        return true;
    }


    public Boolean addSelectedAugment(SelectedAugmentDto selectedAugmentDto) {
        Optional<Game> optGame = gameRepository.findById(selectedAugmentDto.getId());
        KieSession ksession = kSessionService.getCompositionSession("test");
        if(optGame.isPresent()){
            Calendar calendar = Calendar.getInstance();
            AugmentEvent augmentEvent = new AugmentEvent();
            augmentEvent.setName(selectedAugmentDto.getAugment());
            augmentEvent.setExecutionTime(calendar.getTime());
            ksession.insert(augmentEvent);
            return true;
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
        kSessionService.resetKsessionHistoryGrade();
        kSessionService.getKsessionHistoryGrade().setGlobal("username", username);
        Result result = new Result();
        kSessionService.getKsessionHistoryGrade().insert(result);
        long ruleFireCount = kSessionService.getKsessionHistoryGrade().fireAllRules();
        System.out.println(ruleFireCount);
        String retValue = result.getValue();
        return retValue;
    }

    public String getHoursPlayed(String username) {
        kSessionService.resetKSessionHoursPlayed();
        kSessionService.getKSessionHoursPlayed().setGlobal("username", username);
        Result result = new Result();
        kSessionService.getKSessionHoursPlayed().insert(result);
        long ruleFireCount = kSessionService.getKSessionHoursPlayed().fireAllRules();
        System.out.println(ruleFireCount);
        String retValue = result.getValue();
        return retValue;
    }


    public String addRoundResult(Long gameId, String type) {
        Game game = positionSessionGameMap.get(gameId);
        KieSession ksession = kSessionService.getPositionSession("test");
        RoundResult roundResult = RoundResult.LOSS;
        if (type.equals("WIN")){
            roundResult = RoundResult.WIN;
        }
        game.setRound(game.getRound() + 1);
        Calendar calendar = Calendar.getInstance();
        RoundResultEvent roundResultEvent = new RoundResultEvent();
        roundResultEvent.setResult(roundResult);
        roundResultEvent.setTimestamp(calendar.getTime());
        ksession.insert(roundResultEvent);
        ksession.fireAllRules();
        gameRepository.save(game);
        return "true";
    }

    public String addGame(Authentication auth) {
        // TODO: Use actual username from user
//        User user = (User) auth.getPrincipal();
        kSessionService.resetPositionSession("test");
        KieSession ksession = kSessionService.getPositionSession("test");
        Game game = new Game();
        game.setPhase(0);
        game.setHp(100);
        game.setPlayer(new Player(100,3,0));
        game.setUsername("test");
        game.setRound(1);
        game.setLevel(3);
        game.setGold(0);
        game.setTurn(1);
        game.setComponents(new ArrayList<>());
        game.setAugments(new ArrayList<>());
        game.setLatestHint(GameAction.SAVE_GOLD);
        game.setCurrentPosition(PlayerPosition.NEUTRAL);
        game.setCurrentPositionTrend(PlayerPositionTrend.NONE);
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            players.add(new Player(100,3,0));
        }
        game.setOtherPlayers(players);
        gameRepository.save(game);
        positionSessionGameMap.put(game.getId(), game);
        ksession.insert(game);
        ksession.insert("GAME START");
        ksession.fireAllRules();
        return game.getId().toString();

    }

    public String addTurn(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        KieSession ksession = kSessionService.getPositionSession("test");
        ksession.delete(ksession.getFactHandle(positionSessionGameMap.get(game.getId())));
        ksession.insert(game);
        Calendar calendar = Calendar.getInstance();
        TurnStartEvent turnStartEvent = new TurnStartEvent();
        turnStartEvent.setTimestamp(calendar.getTime());
        ksession.insert(turnStartEvent);
        ksession.fireAllRules();
        gameRepository.save(game);
        positionSessionGameMap.put(game.getId(), game);
        return "true";
    }

    public String actionClassification(String gameId) {
        KieSession ksession = kSessionService.getActionClassification();
        Game game = gameRepository.findById(Long.valueOf(gameId)).get();
        ksession.insert(game);
        ksession.fireAllRules();
        ksession.delete(ksession.getFactHandle(game));
        gameRepository.save(game);
        return game.getLatestHint().toString();
    }

    public void changeOtherPlayer(OtherPlayerDto otherPlayerDto) {
        Game game = gameRepository.findById(otherPlayerDto.getGameId()).get();
        game.changePlayer(otherPlayerDto.getId(), otherPlayerDto.getHp(), otherPlayerDto.getLevel(), otherPlayerDto.getGold());
        gameRepository.save(game);
        Game inMemoryGame = positionSessionGameMap.get(game.getId());
        inMemoryGame.changePlayer(otherPlayerDto.getId(), otherPlayerDto.getHp(), otherPlayerDto.getLevel(), otherPlayerDto.getGold());
    }

    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow();
    }
}
