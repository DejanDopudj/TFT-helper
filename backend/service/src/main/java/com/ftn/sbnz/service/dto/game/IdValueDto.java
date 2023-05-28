package com.ftn.sbnz.service.dto.game;

public class IdValueDto {
    private long id;
    private int value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IdValueDto() {
    }

    public IdValueDto(long id, int value) {
        this.id = id;
        this.value = value;
    }
}
