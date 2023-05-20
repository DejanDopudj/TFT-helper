package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleService {
    @Autowired
    CompositionAugmentRepository compositionAugmentRepository;
    @Autowired
    AugmentRepository augmentRepository;
    @Autowired
    ChampionRepository championRepository;
    @Autowired
    ChampionComponentRepository championComponentRepository;

    public double getAugmentCompositionConnection(Augment augment, Composition composition){
        if(composition == null){
            return 1;
        }
        return (double) compositionAugmentRepository.findByAugmentAndComposition(augment, composition).get(0).getConnection() / 100;
    }

    public List<Component> getMissingComponents(Carry carry, List<Component> components){
        List<ChampionComponent> champComponents = championComponentRepository.findByChampionAndConnectionIsGreaterThan(championRepository.findById(carry.getName()).get(), 50);
        champComponents.removeIf(championComponent -> components.contains(championComponent.getComponent()));
        return  champComponents.stream()
                .map(ChampionComponent::getComponent)
                .collect(Collectors.toList());
    }
}
