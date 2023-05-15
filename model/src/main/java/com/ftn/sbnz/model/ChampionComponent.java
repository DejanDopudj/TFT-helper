package com.ftn.sbnz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampionComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "champion_name")
    private Champion champion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "component_name")
    private Component component;

    private int connection;
}
