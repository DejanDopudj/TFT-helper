package com.ftn.sbnz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Augment {

    @Id
    private String name;

    private AugmentClass augmentClass;

    private double averagePlace;
}
