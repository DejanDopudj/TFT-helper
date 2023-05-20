package com.ftn.sbnz.model;

import com.google.protobuf.MapEntry;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tools.ant.taskdefs.PathConvert;
import org.springframework.data.util.Pair;
import  java.util.AbstractMap.SimpleEntry;

import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int round;
    private Streak streak = Streak.NONE;
    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Component> components;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Champion> champions;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Augment> augments;
    @Transient
    private List<SimpleEntry<Augment, Double>> augmentChoice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Composition composition;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> otherPlayers;
    @Transient
    private Map<Composition, Double> compValue;
    @Transient
    private Double phase;
    private int hp;

    private int level;
    private int gold;
    public void addCompValue(Composition composition, double value){
        compValue.put(composition,value);
    }
    public void addAugment(Augment augment){
        augments.add(augment);
    }
    public void addAugmentChoiceValue(Augment augment, Double value) {
        if(augmentChoice.get(0).getKey().getName().equals(augment.getName())){
            augmentChoice.set(0, new SimpleEntry<>(augment,value));
        }
        else if(augmentChoice.get(1).getKey().getName().equals(augment.getName())){
            augmentChoice.set(1, new SimpleEntry<>(augment,value));
        }
        else
            augmentChoice.set(2, new SimpleEntry<>(augment,value));
    }

    public void setPhase(int value){
        phase = (double) value;
    }
    public void setPhase(double value){
        phase = value;
    }


}
