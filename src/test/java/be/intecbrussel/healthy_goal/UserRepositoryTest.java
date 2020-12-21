package be.intecbrussel.healthy_goal;

import be.intecbrussel.healthy_goal.dao.UserDao;
import be.intecbrussel.healthy_goal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserDao userDao;

    @Test
    public void test() {
        User user = userDao.save(new User(101L, "fat yvo", 95, 1.76));
        user.getWeights().put(System.currentTimeMillis(), 12.0);
        userDao.save(user);
    }
}