package be.intecbrussel.healthy_goal.model;

import lombok.Data;

import javax.persistence.*;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Data
@Entity
@Table(name = "users")
public class User implements Principal {
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String picture;
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
        weights = new TreeMap<>();
    }

    // GETTERS & SETTERS

    public Map<Long, Double> getWeights() {
        return new TreeMap<>(this.weights);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
        this.currentBMI = currentWeight / Math.pow(height, 2.0D);
        this.healthyMaxWeight = 25.0D * Math.pow(height, 2.0D);
        this.healthyMinWeight = 18.5D * Math.pow(height, 2.0D);
        if (this.currentBMI > 25.0D) {
            this.weightToLose = this.currentWeight - this.healthyMaxWeight;
        } else {
            this.weightToLose = 0.0D;
        }
        if(this.currentWeight>40D) {
            weights.put(System.currentTimeMillis(), currentWeight);
        }
    }

    public void clearWeights() {
        weights.clear();
        setCurrentWeight(0D);
        setHeight(0D);
    }

    public void deleteValueByKey(Long key) {
        weights.remove(key);
    }

    public void setLastAddedValue() {
        if (!weights.isEmpty()) {
            long lastTimestamp = Collections.max(weights.keySet());
            currentWeight = weights.get(lastTimestamp);
            currentBMI = currentWeight / Math.pow(height, 2.0D);
        } else {
            setCurrentWeight(0D);
            setHeight(0D);
        }
    }

    @Override
    public String getName() {
        return email;
    }
}
