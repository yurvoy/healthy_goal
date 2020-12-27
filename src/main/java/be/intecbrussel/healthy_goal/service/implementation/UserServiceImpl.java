package be.intecbrussel.healthy_goal.service.implementation;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.framework.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElse(null);
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }
}
