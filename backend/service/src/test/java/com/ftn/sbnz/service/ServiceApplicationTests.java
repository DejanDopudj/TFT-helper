package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.event.AugmentEvent;
import com.ftn.sbnz.model.AugmentLocation;
import com.ftn.sbnz.service.repository.*;
import com.ftn.sbnz.service.service.RuleService;
import org.drools.template.ObjectDataCompiler;
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
	ChampionRepository championRepository;
	@Autowired
	AugmentLocationRepository augmentLocationRepository;
	@Autowired
	ChampionLocationRepository championLocationRepository;

	@Autowired
	RuleService ruleService;


	@Test
	void forwardChaining() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession ksession = kc.newKieSession("rulesKsession");
		Game game = createGame();
		List<Double> list = new ArrayList<>(Arrays.asList(1.0, 1.1, 1.2, 1.5));
		Calendar calendar = Calendar.getInstance();
		ksession.insert(game);
		AugmentEvent augmentEvent = new AugmentEvent();
		augmentEvent.setName("Gadgeteen_heart");
		augmentEvent.setExecutionTime(calendar.getTime());
		ksession.insert(augmentEvent);
		ksession.setGlobal("ruleService", ruleService);
		ksession.setGlobal("augmentEventConection", list);
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
	void backwardChaining2() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession ksession = kc.newKieSession("backwardKsession2");
		Champion champion1 = championRepository.findAll().get(0);
		Champion champion2 = championRepository.findAll().get(1);
		ksession.insert(champion1);
		ksession.insert(champion2);
		List<ChampionLocation> championLocations = championLocationRepository.findAll();
		for(ChampionLocation championLocation : championLocations){
			ksession.insert(championLocation);
		}
		ksession.getAgenda().getAgendaGroup("areConnectedGroup2").setFocus();
		long ruleFireCount = ksession.fireAllRules();
		System.out.println(ruleFireCount);
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
		game.setComponents(new ArrayList<>(Arrays.asList(components.get(0),components.get(1),components.get(2))));
		for(Composition composition : compositions){
			game.getCompValue().put(composition, 0.0);
		}
		game.setPhase(0);
		game.setHp(0);
		game.setLevel(1);
		game.setGold(0);
		return game;
	}

	@Test
	void generateTemplate() {
		InputStream template = ServiceApplicationTests.class
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

		KieSession ksession = createKieSessionFromDRL(drl);

		ksession.setGlobal("username", "test");

		doTest(ksession);
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

	private void doTest(KieSession ksession){
		Game game = new Game();
		game.setPlace(1);
		game.setUsername("test");
		Game game2 = new Game();
		game2.setPlace(4);
		game.setUsername("test2");

		ksession.insert(game);
		ksession.insert(game2);
		long ruleFireCount = ksession.fireAllRules();
		System.out.println(ruleFireCount);
	}

}