package com.ftn.sbnz.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class Item {
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Component> components;
}
