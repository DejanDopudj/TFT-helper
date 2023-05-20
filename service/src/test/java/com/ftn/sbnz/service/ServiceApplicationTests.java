package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.event.AugmentEvent;
import com.ftn.sbnz.model.event.RoundResultEvent;
import com.ftn.sbnz.model.AugmentLocation;
import com.ftn.sbnz.service.repository.*;
import com.ftn.sbnz.service.service.RuleService;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.drools.template.ObjectDataCompiler;

import java.io.InputStream;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class ServiceApplicationTests {

	@Autowired
	AugmentRepository augmentRepository;
	@Autowired
	CompositionRepository compositionRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ComponentRepository componentRepository;
	@Autowired
	AugmentLocationRepository augmentLocationRepository;

	@Autowired
	RuleService ruleService;


	@Test
	void forwardChaining() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession ksession = kc.newKieSession("rulesKsession");
		Game game = createGame();

		ksession.insert(game);
		AugmentEvent augmentEvent = new AugmentEvent();
		augmentEvent.setName("Gadgeteens");
		augmentEvent.setExecutionTime(new Date());
		ksession.insert(augmentEvent);
		ksession.setGlobal("ruleService", ruleService);
		long ruleFireCount = ksession.fireAllRules();
		System.out.println(ruleFireCount);
	}

	@Test
	void backwardChaining() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession ksession = kc.newKieSession("backwardKsession");
		Augment augment1 = augmentRepository.findAll().get(1);
		Augment augment2 = augmentRepository.findAll().get(5);
		ksession.insert(augment1);
		ksession.insert(augment2);
		List<AugmentLocation> augmentLocations = augmentLocationRepository.findAll();
		for(AugmentLocation augmentLocation : augmentLocations){
			ksession.insert(augmentLocation);
		}
		ksession.getAgenda().getAgendaGroup("areConnectedGroup").setFocus();
		long ruleFireCount = ksession.fireAllRules();
		System.out.println(ruleFireCount);
	}

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

	private KieSession createKieSessionFromDRL(String drl) {
		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(drl, ResourceType.DRL);

		Results results = kieHelper.verify();

		if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
			List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
			for (Message message : messages) {
				System.out.println("Error: " + message.getText());
			}

			throw new IllegalStateException("Compilation errors were found. Check the logs.");
		}

		return kieHelper.build().newKieSession();
	}

	public Game createGame(){
		List<Augment> augments = augmentRepository.findAll();
		Game game = new Game();
		game.setUsername("test");
		game.setChampions(new ArrayList<>());
		game.setAugments(new ArrayList<>());
		game.setAugmentChoice(IntStream.range(0, 3)
				.mapToObj(i -> new AbstractMap.SimpleEntry<>(augments.get(i), 0.0))
				.collect(Collectors.toList()));
		List<Composition> compositions = compositionRepository.findAll();
		List<Component> components = componentRepository.findAll();
		game.setItems(new ArrayList<>());
		game.setComposition(null);
		game.setCompValue(new HashMap<>());
		game.setComponents(new ArrayList<>(Arrays.asList(components.get(0),components.get(1),components.get(3))));
		for(Composition composition : compositions){
			game.getCompValue().put(composition, 0.0);
		}
		game.setPhase(0);
		game.setHp(0);
		game.setLevel(1);
		game.setGold(0);
		return game;
	}
}