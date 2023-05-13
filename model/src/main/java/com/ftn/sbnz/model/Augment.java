package com.ftn.sbnz.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Augment {

    @Id
    private String name;

    private AugmentClass augmentClass;
}
