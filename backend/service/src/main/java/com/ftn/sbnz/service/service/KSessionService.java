package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.Game;
import com.ftn.sbnz.model.GameHistoryTemplate;
import com.ftn.sbnz.model.Grade;
import com.ftn.sbnz.model.HoursPlayedTemplate;
import com.ftn.sbnz.service.repository.GameRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.cdi.KSession;
import org.kie.api.io.ResourceType;
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

    private KieSession ksessionHistoryGrade;
    private KieSession kSessionHoursPlayed;

    public KieSession getKsessionHistoryGrade(){
        if(ksessionHistoryGrade == null){
            createHistoryGrade();
        }
        return ksessionHistoryGrade;
    }
    public KieSession getkSessionHoursPlayed(){
        if(kSessionHoursPlayed == null){
            createHoursPlayed();
        }
        return kSessionHoursPlayed;
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
