package be.intecbrussel.healthy_goal.dao;

import be.intecbrussel.healthy_goal.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("testDao")
public class FakeUserDataAccessService implements UserDao {

    public static List<User> DB = new ArrayList<>();

    @Override
    public int addUser(UUID id, User user) {
        DB.add(new User(id, user.getNAME(), user.getGENDER(), user.getSTART_WEIGHT(), user.getHEIGHT()));
        return 1;
    }
}
