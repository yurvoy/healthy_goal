package be.intecbrussel.healthy_goal.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "user_weight", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "timestamp")
    @Column(name = "weight")
    private Map<Long, Double> weights = new HashMap<>();

    public Long getId() {
        return id;
    }

    public Map<Long, Double> getWeights() {
        return weights;
    }
}
