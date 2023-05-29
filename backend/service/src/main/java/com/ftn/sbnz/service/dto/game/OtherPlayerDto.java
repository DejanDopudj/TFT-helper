package com.ftn.sbnz.service.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherPlayerDto {
    private int row;
    private int hp;
    private int level;
    private int gold;
    private Long gameId;
}
