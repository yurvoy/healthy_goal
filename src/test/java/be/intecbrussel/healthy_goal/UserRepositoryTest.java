package be.intecbrussel.healthy_goal;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.Advies;
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
        User user = userDao.save(new User("fakeID", "FooFoo", "Foo@gmail.com", AuthProvider.GOOGLE));
        userDao.save(user);
        System.out.println(Advies.getAdvies());
    }

}