package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.Game;
import com.ftn.sbnz.service.dto.game.*;
import com.ftn.sbnz.service.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> increaseLevel(@Valid @RequestBody IdDto idDto) {
        return ResponseEntity.ok(gameService.increaseLevel(idDto.getId()));
    }

    @PostMapping("/decrease-level")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> decreaseLevel(@Valid @RequestBody IdDto idDto) {
        return ResponseEntity.ok(gameService.decreaseLevel(idDto.getId()));
    }

    @PostMapping("/set-gold")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> setGold(@Valid @RequestBody IdValueDto idValueDto) {
        return ResponseEntity.ok(gameService.setGold(idValueDto.getId(),idValueDto.getValue()));
    }

    @PostMapping("/set-hp")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> setHp(@Valid @RequestBody IdValueDto idValueDto) {
        return ResponseEntity.ok(gameService.setHP(idValueDto.getId(),idValueDto.getValue()));
    }

    @PostMapping("/set-place")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> setPlace(@Valid @RequestBody IdValueDto idValueDto) {
        return ResponseEntity.ok(gameService.setPlace(idValueDto.getId(),idValueDto.getValue()));
    }

    @PostMapping("/add-component")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> addComponent(@Valid @RequestBody GameComponentDto gameComponentDto) {
        return ResponseEntity.ok(gameService.addComponent(gameComponentDto.getId(),gameComponentDto.getComponentName()));
    }
    @PostMapping("/add-augments")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> addAugments(@Valid @RequestBody GameAugmentsDto gameAugmentsDto) {
        return ResponseEntity.ok(gameService.addAugments(gameAugmentsDto));
    }

    @PostMapping("/add-selected-augment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> addSelectedAugment(@Valid @RequestBody SelectedAugmentDto selectedAugmentDto) {
        return ResponseEntity.ok(gameService.addSelectedAugment(selectedAugmentDto));
    }


    @PostMapping("/get-augment-connection")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAugmentConnection(@Valid @RequestBody AugmentConnectionDto augmentConnectionDto) {
        return gameService.getAugmentConnection(augmentConnectionDto);
    }

    @PostMapping("/get-champion-connection")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getChampionConnection(@Valid @RequestBody ChampionConnectionDto championConnectionDto) {
        return gameService.getChampionConnection(championConnectionDto);
    }


    @PostMapping("/get-history-grade")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getHistoryGrade(@Valid @RequestBody String username) {
        return gameService.getHistoryGrade(username);
    }

    @PostMapping("/get-hours-played")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getHoursPlayed(@Valid @RequestBody String username) {
        return gameService.getHoursPlayed(username);
    }

    @PostMapping("/round-result")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addRoundResult(@Valid @RequestBody RoundResultDto roundResultDto) {
        return gameService.addRoundResult(roundResultDto.getGameId(), roundResultDto.getResult());
    }

    @PostMapping("/game")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addGame(Authentication auth) {
        return gameService.addGame(auth);
    }

    @PostMapping("/get-game")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Game getGameById(@Valid @RequestBody IdDto idDto) {
        return gameService.getGameById(idDto.getId());
    }

    @PostMapping("/turn")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addTurn(@Valid @RequestBody IdDto idDto) {
        return gameService.addTurn(idDto.getId());
    }

    @PostMapping("/action-classification")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String actionClassification(@Valid @RequestBody GameIdDto gameIdDto) {
        return gameService.actionClassification(gameIdDto.getGameId());
    }

    @PostMapping("/change-other-player")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void changeOtherPlayer(@Valid @RequestBody OtherPlayerDto otherPlayerDto) {
        gameService.changeOtherPlayer(otherPlayerDto);
    }


}
