package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;

import java.util.UUID;

public interface UserDao {

    int addUser(UUID id, User user);

    default int addUser(User user) {
        UUID id = UUID.randomUUID();
        return addUser(id, user);
    }
}
