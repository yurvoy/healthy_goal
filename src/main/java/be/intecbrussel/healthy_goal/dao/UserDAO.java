package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


}
