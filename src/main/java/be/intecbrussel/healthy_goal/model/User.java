package be.intecbrussel.healthy_goal.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class User implements UserDetails, OAuth2User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String password;

    @Column
    private String authProvider;

    @Column
    private String providerId;

    @Transient
    private Map<String, Object> attributes;

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


    // GETTERS

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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

    // SETTER

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
        if (weights.isEmpty()) {
            weights = new HashMap<>();
        }
        weights.put(System.currentTimeMillis(), currentWeight);
    }

}
