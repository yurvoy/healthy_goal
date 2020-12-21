package be.intecbrussel.healthy_goal.service;

import be.intecbrussel.healthy_goal.dao.UserDao;
import be.intecbrussel.healthy_goal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("testDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public List<User> getAllUsers (){
        return userDao.getAllUsers();
    }

    public Optional<User> getUserById(UUID id){
        return userDao.getUserById(id);
    }

    public int deleteUser (UUID id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser (UUID id, double newWeight) {
        return userDao.updateUserById(id, newWeight);
    }
}
