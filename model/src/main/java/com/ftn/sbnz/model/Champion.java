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
public class Champion {

    @Id
    private String name;

    /*@ElementCollection
    private List<Trait> traits;*/

    private int cost;
}
