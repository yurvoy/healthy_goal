package be.intecbrussel.healthy_goal.service;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.AuthProvider;
import be.intecbrussel.healthy_goal.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserDAO userDAO;

    public void save(User user){

    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public void createNewOAuth2User(String email, String name, String picture, AuthProvider provider) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFullName(name);
        newUser.setPicture(picture);
        newUser.setProvider(provider);

        userDAO.save(newUser);
    }

    public void updateOAuth2User(User user, String name, String picture, AuthProvider provider) {
        user.setFullName(name);
        user.setPicture(picture);
        user.setProvider(provider);

        userDAO.save(user);
    }
}
