package com.ftn.sbnz.model;
public class HoursPlayedTemplate {
    private int minGames;
    private int maxGames;
    private String hoursPlayed;

    public HoursPlayedTemplate() {
    }

    public HoursPlayedTemplate(int minGames, int maxGames, String hoursPlayed) {
        this.minGames = minGames;
        this.maxGames = maxGames;
        this.hoursPlayed = hoursPlayed;
    }

    public int getMinGames() {
        return minGames;
    }

    public void setMinGames(int minGames) {
        this.minGames = minGames;
    }

    public int getMaxGames() {
        return maxGames;
    }

    public void setMaxGames(int maxGames) {
        this.maxGames = maxGames;
    }

    public String getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(String hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

}
