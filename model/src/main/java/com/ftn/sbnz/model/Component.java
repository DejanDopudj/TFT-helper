package com.ftn.sbnz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Component {
    @Id
    private String name;
}
