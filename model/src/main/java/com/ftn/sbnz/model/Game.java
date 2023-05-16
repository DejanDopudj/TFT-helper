package com.ftn.sbnz.model;

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

    private int round;
    private Streak streak = Streak.NONE;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Component> components;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Champion> champions;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Augment> augments;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> otherPlayers;

}
