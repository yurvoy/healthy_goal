package be.intecbrussel.healthy_goal;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.AuthProvider;
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
        User user = userDao.save(new User("fakeID", "FooFoo", "Foo@gmail.com", AuthProvider.GOOGLE));
        userDao.save(user);

        //Remove Map data test
        user.getWeights().put(123456L, 12.0);
        user.getWeights().put(123457L, 13.0);
        userDao.save(user);
        user.getWeights().remove(123457L);
        userDao.save(user);
    }

}