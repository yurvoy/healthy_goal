package be.intecbrussel.healthy_goal;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserDAO userDao;

    @Test
    public void test() {
        //Save user test
        User user = new User();
        user.setId("id");
        user.setFullName("yvo urvoy");
        user.setEmail("yvonnick.urvoy@gmail.com");
        user.setPicture("https://fakeurl.com/epfoakeafp.jpg");
        userDao.save(user);

        //Remove Map data test
        user.setHeight(1.76);
        user.getWeights().put(123456L, 75.0);
        user.getWeights().put(123457L, 71.0);
        userDao.save(user);
        user.getWeights().remove(123457L);
        userDao.save(user);
        user.setLastAddedValue();
        userDao.save(user);
    }


}