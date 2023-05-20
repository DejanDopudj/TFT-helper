package com.ftn.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carry {
    private String name;
    private Long id;
    private String username;
    private boolean regenerateCarry = true;
}
