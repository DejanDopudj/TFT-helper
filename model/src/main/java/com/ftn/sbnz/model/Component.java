package com.ftn.sbnz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ftn.sbnz.model.Champion;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Component {
    @Id
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "champion_name")
    private Champion carry;

}
