package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.service.repository.AugmentLocationRepository;
import com.ftn.sbnz.service.repository.ChampionLocationRepository;
import com.ftn.sbnz.service.repository.GameRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
@Scope("singleton")
@NoArgsConstructor
@AllArgsConstructor
public class KSessionService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ChampionLocationRepository championLocationRepository;

    @Autowired
    private AugmentLocationRepository augmentLocationRepository;
    @Autowired
    private RuleService ruleService;

    private KieSession ksessionHistoryGrade;
    private KieSession kSessionHoursPlayed;
    private KieSession augmentConnection;
    private KieSession championConnection;
    private Map<String, KieSession> mapCompositionSession = new HashMap<>();
    private Map<String, KieSession> mapPositionSession = new HashMap<>();
    private KieSession actionClassification;

    public KieSession getCompositionSession(String username){
        if(!mapCompositionSession.containsKey(username)){
            createCompositionSession(username);
        }
        return mapCompositionSession.get(username);
    }

    public void resetCompositionSession(String username) {
        if (mapCompositionSession.containsKey(username)) {
            mapCompositionSession.get(username).dispose();
        }
        createCompositionSession(username);
    }

    private void createCompositionSession(String username) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("rulesKsession");
        List<Double> list = new ArrayList<>(Arrays.asList(1.0, 1.1, 1.2, 1.5));
        ksession.setGlobal("ruleService", ruleService);
        ksession.setGlobal("augmentEventConection", list);
        mapCompositionSession.put(username, ksession);
    }

    public KieSession getPositionSession(String username){
        if(!mapPositionSession.containsKey(username)){
            createPositionSession(username);
        }
        return mapPositionSession.get(username);
    }

    public void resetPositionSession(String username) {
        if (mapPositionSession.containsKey(username)) {
            mapPositionSession.get(username).dispose();
        }
        createPositionSession(username);
    }

    private void createPositionSession(String username) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("cepKsession");
        mapPositionSession.put(username, ksession);
    }


    public KieSession getActionClassification(){
        if(actionClassification == null){
            createActionClassification();
        }
        return actionClassification;
    }

    public KieSession getKsessionHistoryGrade(){
        if(ksessionHistoryGrade == null){
            createHistoryGrade();
        }
        return ksessionHistoryGrade;
    }

    public void resetKsessionHistoryGrade() {
        if (ksessionHistoryGrade != null) {
            ksessionHistoryGrade.dispose();
        }
        createHistoryGrade();
    }

    public KieSession getKSessionHoursPlayed(){
        if(kSessionHoursPlayed == null){
            createHoursPlayed();
        }
        return kSessionHoursPlayed;
    }

    public void resetKSessionHoursPlayed() {
        if (kSessionHoursPlayed != null) {
            kSessionHoursPlayed.dispose();
        }
        createHoursPlayed();
    }

    public KieSession getKSessionAugmentConnection(){
        if(augmentConnection == null){
            createAugmentConnection();
        }
        return augmentConnection;
    }

    private void createAugmentConnection() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        augmentConnection = kc.newKieSession("backwardKsession");
        List<AugmentLocation> augmentLocations = augmentLocationRepository.findAll();
        for(AugmentLocation augmentLocation : augmentLocations){
            augmentConnection.insert(augmentLocation);
        }
    }

    public KieSession getkSessionChampionConnection(){
        if(championConnection == null){
            createChampionConnection();
        }
        return championConnection;
    }

    private void createActionClassification(){
        InputStream template = KSessionService.class
                .getResourceAsStream("/template/action-classification.drt");
        List<ClassificationTemplate> data = new ArrayList<>();

        // SAVE GOLD
        data.add(new ClassificationTemplate(25, 100, 0, 1000,
                0, 10, 0, 7, Streak.LOSS,
                GameAction.SAVE_GOLD));
        data.add(new ClassificationTemplate(25, 100, 0, 49,
                0, 10, 0, 7, Streak.NONE,
                GameAction.SAVE_GOLD));

        // LEVEL UP
        data.add(new ClassificationTemplate(25, 100, 0, 1000,
                0, 6, 0, 4, Streak.WIN,
                GameAction.LEVEL_UP));
        data.add(new ClassificationTemplate(25, 100, 50, 1000,
                0, 6, 0, 4, Streak.NONE,
                GameAction.LEVEL_UP));
        data.add(new ClassificationTemplate(25, 100, 50, 1000,
                0, 7, 5, 7, Streak.NONE,
                GameAction.LEVEL_UP));
        data.add(new ClassificationTemplate(0, 25, 4, 1000,
                0, 10, 0, 7, Streak.NONE,
                GameAction.LEVEL_UP));

        // ROLL
        data.add(new ClassificationTemplate(25, 100, 0, 1000,
                7, 10, 0, 4, Streak.WIN,
                GameAction.ROLL));
        data.add(new ClassificationTemplate(25, 100, 50, 1000,
                7, 10, 0, 4, Streak.NONE,
                GameAction.ROLL));
        data.add(new ClassificationTemplate(25, 100, 50, 1000,
                8, 10, 5, 7, Streak.NONE,
                GameAction.ROLL));
        data.add(new ClassificationTemplate(0, 25, 0, 3,
                0, 10, 0, 7, Streak.NONE,
                GameAction.ROLL));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        actionClassification = createKieSessionFromDRL(drl);
    }

    private void createChampionConnection() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        championConnection = kc.newKieSession("backwardKsession2");
        List<ChampionLocation> championLocations = championLocationRepository.findAll();
        for(ChampionLocation championLocation : championLocations){
            championConnection.insert(championLocation);
        }
    }


    private void createHoursPlayed(){
        InputStream template = GameRepository.class
                .getResourceAsStream("/template/hoursPlayed.drt");
        List<HoursPlayedTemplate> data = new ArrayList<>();

        data.add(new HoursPlayedTemplate(1,5, "~3"));
        data.add(new HoursPlayedTemplate(5,10, "~5"));
        data.add(new HoursPlayedTemplate(10,20, "~10"));
        data.add(new HoursPlayedTemplate(20,50, "~25"));
        data.add(new HoursPlayedTemplate(50,100, "~50"));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

//        System.out.println(drl);

        kSessionHoursPlayed = createKieSessionFromDRL(drl);

        List<Game> games = gameRepository.findAll();

        for(Game game : games){
            kSessionHoursPlayed.insert(game);
        }
    }

    private void createHistoryGrade(){
        InputStream template = GameService.class
                .getResourceAsStream("/template/gameHistory.drt");
        List<GameHistoryTemplate> data = new ArrayList<>();

        data.add(new GameHistoryTemplate(1,2, Grade.SP));
        data.add(new GameHistoryTemplate(2,3, Grade.S));
        data.add(new GameHistoryTemplate(3,4, Grade.A));
        data.add(new GameHistoryTemplate(4,5, Grade.B));
        data.add(new GameHistoryTemplate(5,6, Grade.C));
        data.add(new GameHistoryTemplate(6,7, Grade.D));
        data.add(new GameHistoryTemplate(7,8, Grade.E));
        data.add(new GameHistoryTemplate(8,9, Grade.F));


        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

//        System.out.println(drl);

        ksessionHistoryGrade = createKieSessionFromDRL(drl);

        List<Game> games = gameRepository.findAll();

        for(Game game : games){
            ksessionHistoryGrade.insert(game);
        }
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }
}
