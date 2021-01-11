package be.intecbrussel.healthy_goal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

@Data
@Entity
public class User{
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthProvider provider;

    @Column
    private String password;

    @Column
    private double height;
    @Column(name = "current_weight")
    private double currentWeight;
    @Column(name = "current_bmi")
    private double currentBMI;
    @Column(name = "healthy_max")
    private double healthyMaxWeight;
    @Column(name = "healthy_min")
    private double healthyMinWeight;
    @Column(name = "to_lose")
    private double weightToLose;
    @ElementCollection
    @CollectionTable(name = "user_timeline", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "date")
    @Column(name = "weight")
    private Map<Long, Double> weights;


    // CONSTRUCTOR

    public User() {
    }

    public User(String id, String name, String email, AuthProvider provider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.provider = provider;
    }


    // GETTERS

    public double getHeight() {
        return this.height;
    }

    public double getCurrentBMI() {
        return this.currentBMI;
    }

    public double getCurrentWeight() {
        return this.currentWeight;
    }

    public double getHealthyMaxWeight() {
        return this.healthyMaxWeight;
    }

    public double getHealthyMinWeight() {
        return this.healthyMinWeight;
    }

    public double getWeightToLose() {
        return this.weightToLose;
    }

    public Map<Long, Double> getWeights() {
        return this.weights;
    }

    public AuthProvider getProvider() {
        return provider;
    }


    // SETTERS

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
        this.currentBMI = this.currentWeight / Math.pow(height, 2.0D);
        this.healthyMaxWeight = 25.0D * Math.pow(height, 2.0D);
        this.healthyMinWeight = 18.5D * Math.pow(height, 2.0D);
        if (this.currentBMI > 25.0D) {
            this.weightToLose = this.currentWeight - this.healthyMaxWeight;
        } else {
            this.weightToLose = 0.0D;
        }
        if (weights == null) {
            weights = new TreeMap<>();
        }
        weights.put(System.currentTimeMillis(), currentWeight);
    }

    public void deleteValueByKey (Long key) {
        weights.remove(key);
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }
}
