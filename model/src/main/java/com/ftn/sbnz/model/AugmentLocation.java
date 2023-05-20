package com.ftn.sbnz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Position;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AugmentLocation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Position(0)

    private String child;
    @Position(1)
    private String parent;
}
