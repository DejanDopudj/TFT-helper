package com.ftn.sbnz.service.dto.game;

public class GameComponentDto {
    private Long id;
    private String componentName;

    public GameComponentDto() {
    }

    public GameComponentDto(Long id, String componentName) {
        this.id = id;
        this.componentName = componentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}
