package com.ftn.sbnz.model;

import com.ftn.sbnz.model.Augment;
import com.ftn.sbnz.model.Composition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private int connection;
}
