package be.intecbrussel.healthy_goal.service.framework;

import be.intecbrussel.healthy_goal.model.User;

public interface UserService {
    User findByEmail(String email);
    User save(User user);
}
