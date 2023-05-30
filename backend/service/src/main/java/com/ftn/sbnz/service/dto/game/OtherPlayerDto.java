package com.ftn.sbnz.service.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherPlayerDto {
    private UUID id;
    private int hp;
    private int level;
    private int gold;
    private Long gameId;
}
