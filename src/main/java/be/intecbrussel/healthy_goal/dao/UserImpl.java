package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("testDao")
public class UserImpl implements UserDao {

    public static List<User> DB = new ArrayList<>();

    @Override
    public int addUser(UUID id, User user) {
        DB.add(new User(id, user.getNAME(), user.getSTART_WEIGHT(), user.getHEIGHT()));
        return 1;
    }

    @Override
    public List<User> getAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getID().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> potentialUser = getUserById(id);
        if (potentialUser.isEmpty()) {
            return 0;
        }
        DB.remove(potentialUser.get());
        return 1;
    }

    @Override
    public int updateUserById(UUID id, double newWeight) {
        return getUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        user.setCurrentWeight(newWeight);
                        user.addWeight(newWeight);
                        DB.set(indexOfUserToUpdate, user);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
