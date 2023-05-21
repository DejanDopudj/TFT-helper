package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.event.AugmentEvent;
import com.ftn.sbnz.model.AugmentLocation;
import com.ftn.sbnz.service.repository.*;
import com.ftn.sbnz.service.service.RuleService;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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