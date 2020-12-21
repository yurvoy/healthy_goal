package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int addUser(UUID id, User user);

    default int addUser(User user) {
        UUID id = UUID.randomUUID();
        return addUser(id, user);
    }

    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    int deleteUserById(UUID id);

    int updateUserById(UUID id, double newWeight);
}
