package com.ftn.sbnz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Augment> augmentChoice;

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
    private int phase;
    private int hp;

    private int level;
    private int gold;
    public void addCompValue(Composition composition, double value){
        compValue.put(composition,value);
    }
    public void addAugment(Augment augment){
        augments.add(augment);
    }


}
