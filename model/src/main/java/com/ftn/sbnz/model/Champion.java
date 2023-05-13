package com.ftn.sbnz.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Champion {

    @Id
    private String name;

    @ElementCollection
    private List<Trait> traits;

    private int cost;
}
