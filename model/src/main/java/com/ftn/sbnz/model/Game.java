package com.ftn.sbnz.model;

import jakarta.persistence.*;

import java.util.List;

public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Component> components;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Champion> champions;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Augment> augments;

    private int hp;

    private int level;
    private int gold;

}
