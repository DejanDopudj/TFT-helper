package com.ftn.sbnz.model;

public class ClassificationTemplate {
    private double minHp;
    private double maxHpIncluded;
    private int minGoldIncluded;
    private int maxGoldIncluded;
    private int minLevelIncluded;
    private int maxLevelIncluded;
    private int minRoundIncluded;
    private int maxRoundIncluded;
    private Streak streak;
    private GameAction action;

    public ClassificationTemplate(double minHp, double maxHpIncluded, int minGoldIncluded, int maxGoldIncluded,
                                  int minLevelIncluded, int maxLevelIncluded, int minRoundIncluded,
                                  int maxRoundIncluded, Streak streak, GameAction action) {
        this.minHp = minHp;
        this.maxHpIncluded = maxHpIncluded;
        this.minGoldIncluded = minGoldIncluded;
        this.maxGoldIncluded = maxGoldIncluded;
        this.minLevelIncluded = minLevelIncluded;
        this.maxLevelIncluded = maxLevelIncluded;
        this.minRoundIncluded = minRoundIncluded;
        this.maxRoundIncluded = maxRoundIncluded;
        this.streak = streak;
        this.action = action;
    }

    public double getMinHp() {
        return minHp;
    }

    public void setMinHp(double minHp) {
        this.minHp = minHp;
    }

    public double getMaxHpIncluded() {
        return maxHpIncluded;
    }

    public void setMaxHpIncluded(double maxHpIncluded) {
        this.maxHpIncluded = maxHpIncluded;
    }

    public int getMinGoldIncluded() {
        return minGoldIncluded;
    }

    public void setMinGoldIncluded(int minGoldIncluded) {
        this.minGoldIncluded = minGoldIncluded;
    }

    public int getMaxGoldIncluded() {
        return maxGoldIncluded;
    }

    public void setMaxGoldIncluded(int maxGoldIncluded) {
        this.maxGoldIncluded = maxGoldIncluded;
    }

    public int getMinLevelIncluded() {
        return minLevelIncluded;
    }

    public void setMinLevelIncluded(int minLevelIncluded) {
        this.minLevelIncluded = minLevelIncluded;
    }

    public int getMaxLevelIncluded() {
        return maxLevelIncluded;
    }

    public void setMaxLevelIncluded(int maxLevelIncluded) {
        this.maxLevelIncluded = maxLevelIncluded;
    }

    public int getMinRoundIncluded() {
        return minRoundIncluded;
    }

    public void setMinRoundIncluded(int minRoundIncluded) {
        this.minRoundIncluded = minRoundIncluded;
    }

    public int getMaxRoundIncluded() {
        return maxRoundIncluded;
    }

    public void setMaxRoundIncluded(int maxRoundIncluded) {
        this.maxRoundIncluded = maxRoundIncluded;
    }

    public Streak getStreak() {
        return streak;
    }

    public void setStreak(Streak streak) {
        this.streak = streak;
    }

    public GameAction getAction() {
        return action;
    }

    public void setAction(GameAction action) {
        this.action = action;
    }
}
