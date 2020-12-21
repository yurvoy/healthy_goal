package be.intecbrussel.healthy_goal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private final UUID ID;
    private final String NAME;
    private final double START_WEIGHT;
    private final double HEIGHT;
    private final double BMI_MAX = 25;
    private final double BMI_MIN = 18.5;

    private double currentWeight;
    private double currentBMI;
    private double healthyMaxWeight;
    private double healthyMinWeight;
    private double weightToLose;
    private Map<Long, Double> timeline;


    public User(@JsonProperty("id") UUID ID,
                @JsonProperty("name") String NAME,
                @JsonProperty("weight") double START_WEIGHT,
                @JsonProperty("height") double HEIGHT) {
        this.ID = ID;
        this.NAME = NAME;
        this.START_WEIGHT = START_WEIGHT;
        this.HEIGHT = HEIGHT;
        this.currentWeight = START_WEIGHT;
        this.currentBMI = currentWeight / Math.pow(HEIGHT, 2);
        this.healthyMaxWeight = BMI_MAX * Math.pow(HEIGHT, 2);
        this.healthyMinWeight = BMI_MIN * Math.pow(HEIGHT, 2);
        if (currentBMI > 25) {
            weightToLose = currentWeight - healthyMaxWeight;
        } else {
            weightToLose = 0;
        }
        this.timeline = new HashMap<>();
    }

    //getters
    public UUID getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public double getSTART_WEIGHT() {
        return START_WEIGHT;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public double getCurrentBMI() {
        return currentBMI;
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

    public double getWeightToLose() {
        return weightToLose;
    }

    public Map<Long, Double> getTimeline() {
        return timeline;
    }

    //weight setter
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void addWeight(double weight) {
        timeline.put(System.currentTimeMillis(), weight);
    }
}
