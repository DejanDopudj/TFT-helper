package com.ftn.sbnz.model;

import jakarta.persistence.*;

@Entity
public class CompositionAugment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "composition_name")
    private Composition composition;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "augment_name")
    private Augment augment;

    private int value;
}
