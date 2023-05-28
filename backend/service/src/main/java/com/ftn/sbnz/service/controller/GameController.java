package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.game.GameAugmentsDto;
import com.ftn.sbnz.service.dto.game.GameComponentDto;
import com.ftn.sbnz.service.dto.game.IdDto;
import com.ftn.sbnz.service.dto.game.IdValueDto;
import com.ftn.sbnz.service.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/increase-level")
    public ResponseEntity<Boolean> increaseLevel(@Valid @RequestBody IdDto idDto) {
        return ResponseEntity.ok(gameService.increaseLevel(idDto.getId()));
    }

    @PostMapping("/decrease-level")
    public ResponseEntity<Boolean> decreaseLevel(@Valid @RequestBody IdDto idDto) {
        return ResponseEntity.ok(gameService.decreaseLevel(idDto.getId()));
    }

    @PostMapping("/set-gold")
    public ResponseEntity<Boolean> setGold(@Valid @RequestBody IdValueDto idValueDto) {
        return ResponseEntity.ok(gameService.setGold(idValueDto.getId(),idValueDto.getValue()));
    }

    @PostMapping("/set-hp")
    public ResponseEntity<Boolean> setHp(@Valid @RequestBody IdValueDto idValueDto) {
        return ResponseEntity.ok(gameService.setHP(idValueDto.getId(),idValueDto.getValue()));
    }

    @PostMapping("/set-place")
    public ResponseEntity<Boolean> setPlace(@Valid @RequestBody IdValueDto idValueDto) {
        return ResponseEntity.ok(gameService.setPlace(idValueDto.getId(),idValueDto.getValue()));
    }


    @PostMapping("/add-component")
    public ResponseEntity<Boolean> addComponent(@Valid @RequestBody GameComponentDto gameComponentDto) {
        return ResponseEntity.ok(gameService.addComponent(gameComponentDto.getId(),gameComponentDto.getComponentName()));
    }
    @PostMapping("/add-augments")
    public ResponseEntity<Boolean> addAugments(@Valid @RequestBody GameAugmentsDto gameAugmentsDto) {
        return ResponseEntity.ok(gameService.addAugments(gameAugmentsDto));
    }


}
