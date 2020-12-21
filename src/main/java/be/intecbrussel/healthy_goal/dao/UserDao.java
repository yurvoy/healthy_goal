package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserDao extends CrudRepository<User, Long> {

}
