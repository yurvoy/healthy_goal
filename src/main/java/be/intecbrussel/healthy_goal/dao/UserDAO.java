package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
