package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.event.RoundResultEvent;
import com.ftn.sbnz.model.event.TurnStartEvent;
import org.drools.template.ObjectDataCompiler;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SystemTwoTests {

    @Test
    void winLossCep() throws InterruptedException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("cepKsession");

        Game game = new Game();
        ksession.insert(game);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.LOSS));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.LOSS));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.LOSS));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.LOSS));
        ksession.fireAllRules();
        Thread.sleep(50);
        ksession.insert(new RoundResultEvent(RoundResult.WIN));
        long ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
    }

    @Test
    public void testPositionEvaluation() throws InterruptedException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("cepKsession");

        ksession.getAgenda().getAgendaGroup("gameStartActivationGroup").setFocus();
        Game game = new Game();
        game.setPlayer(new Player(76 , 4, 37));
        List<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(78, 5));
        otherPlayers.add(new Player(48, 4));
        otherPlayers.add(new Player(75, 5));
        otherPlayers.add(new Player(46, 4));
        otherPlayers.add(new Player(81, 6));
        otherPlayers.add(new Player(63, 7));
        otherPlayers.add(new Player(89, 5));
        game.setOtherPlayers(otherPlayers);
        game.setStreak(Streak.WIN);
        ksession.insert(game);
        ksession.fireAllRules();

        ksession.getAgenda().getAgendaGroup("gameStartActivationGroup").clear();
        ksession.insert(new TurnStartEvent());
        ksession.fireAllRules();

        Thread.sleep(25);

        game.getPlayer().setGold(55);
        game.getPlayer().setHp(72);
        game.getPlayer().setLevel(6);
        game.setStreak(Streak.WIN);
        ksession.getAgenda().getAgendaGroup("gameStartActivationGroup").setFocus();
        ksession.insert(new TurnStartEvent());
        ksession.fireAllRules();

        Thread.sleep(25);

        game.getPlayer().setGold(69);
        game.getPlayer().setHp(71);
        game.getPlayer().setLevel(8);
        game.setStreak(Streak.WIN);
        ksession.getAgenda().getAgendaGroup("gameStartActivationGroup").setFocus();
        ksession.insert(new TurnStartEvent());
        ksession.fireAllRules();

        Thread.sleep(25);

        game.getPlayer().setGold(80);
        game.getPlayer().setHp(70);
        game.getPlayer().setLevel(9);
        game.setStreak(Streak.WIN);
        ksession.getAgenda().getAgendaGroup("gameStartActivationGroup").setFocus();
        ksession.insert(new TurnStartEvent());
        ksession.fireAllRules();

        ksession.dispose();
    }

    @Test
    void generateTemplate() {
        InputStream template = ServiceApplicationTests.class
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

        System.out.println(drl);

        KieSession ksession = createKieSessionFromDRL(drl);

        doTest(ksession);
    }

    private void doTest(KieSession ksession){
        Player player = new Player(50, 5, 57);
        Game game = new Game();
        game.setRound(4);
        game.setStreak(Streak.WIN);
        game.setPlayer(player);

        ksession.insert(game);
        ksession.fireAllRules();
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
