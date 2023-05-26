package com.ftn.sbnz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private int hp;
    private int level;
    private int gold;

    public Player(int hp, int level, int gold) {
        this.id = UUID.randomUUID();
        this.hp = hp;
        this.level = level;
        this.gold = gold;
    }

    public Player(int hp, int level) {
        this.id = UUID.randomUUID();
        this.hp = hp;
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
