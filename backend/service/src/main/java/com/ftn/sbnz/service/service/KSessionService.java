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
import java.util.ArrayList;
import java.util.List;

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

    private KieSession ksessionHistoryGrade;
    private KieSession kSessionHoursPlayed;
    private KieSession augmentConnection;
    private KieSession championConnection;

    public KieSession getKsessionHistoryGrade(){
        if(ksessionHistoryGrade == null){
            createHistoryGrade();
        }
        return ksessionHistoryGrade;
    }
    public KieSession getKSessionHoursPlayed(){
        if(kSessionHoursPlayed == null){
            createHoursPlayed();
        }
        return kSessionHoursPlayed;
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

        data.add(new HoursPlayedTemplate(0,5, "~3"));
        data.add(new HoursPlayedTemplate(6,10, "~5"));
        data.add(new HoursPlayedTemplate(11,20, "~10"));
        data.add(new HoursPlayedTemplate(20,50, "~25"));
        data.add(new HoursPlayedTemplate(50,100, "~50"));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        System.out.println(drl);

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

        System.out.println(drl);

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
