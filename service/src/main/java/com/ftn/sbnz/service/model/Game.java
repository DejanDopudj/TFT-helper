package com.ftn.sbnz.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
