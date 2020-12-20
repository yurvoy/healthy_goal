package be.intecbrussel.healthy_goal.model;

import java.util.UUID;

public class User {

    private final UUID ID;
    private final String NAME;
    private final int GENDER;
    private final double START_WEIGHT;
    private final double HEIGHT;
    private final double MAX = 25;
    private final double MIN = 18.5;

    private double currentWeight;
    private double healthyMaxWeight;
    private double healthyMinWeight;


    public User(UUID ID, String NAME, int GENDER, double START_WEIGHT, double HEIGHT) {
        this.ID = ID;
        this.NAME = NAME;
        this.GENDER = GENDER;
        this.START_WEIGHT = START_WEIGHT;
        this.HEIGHT = HEIGHT;
        this.currentWeight = START_WEIGHT;
        this.healthyMaxWeight = MAX * Math.pow(HEIGHT, 2);
        this.healthyMinWeight = MIN * Math.pow(HEIGHT, 2);
    }

    //getters
    public UUID getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getGENDER() {
        return GENDER;
    }

    public double getSTART_WEIGHT() {
        return START_WEIGHT;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public double getHealthyMaxWeight() {
        return healthyMaxWeight;
    }

    public double getHealthyMinWeight() {
        return healthyMinWeight;
    }

    //setter
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
}
