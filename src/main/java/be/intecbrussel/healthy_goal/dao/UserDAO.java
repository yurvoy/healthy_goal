package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
