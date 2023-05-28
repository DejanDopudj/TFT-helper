package com.ftn.sbnz.service.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameAugmentsDto {
    private Long id;
    private List<String> augmentNames;
}
