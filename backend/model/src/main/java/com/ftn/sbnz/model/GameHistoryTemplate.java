package com.ftn.sbnz.model;

public class GameHistoryTemplate {
    private int minPlace;
    private int maxPlace;
    private Grade grade;


    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    public int getMinPlace() {
        return minPlace;
    }

    public void setMinPlace(int minPlace) {
        this.minPlace = minPlace;
    }

    public int getMaxPlace() {
        return maxPlace;
    }

    public void setMaxPlace(int maxPlace) {
        this.maxPlace = maxPlace;
    }

    public GameHistoryTemplate() {
    }
    public GameHistoryTemplate(int minPlace, int maxPlace, Grade grade) {
        this.minPlace = minPlace;
        this.maxPlace = maxPlace;
        this.grade = grade;
    }

}
