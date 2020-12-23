package be.intecbrussel.healthy_goal;

import be.intecbrussel.healthy_goal.dao.User2Repository;
import be.intecbrussel.healthy_goal.model.User2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class User2RepositoryTest {

    @Autowired
    User2Repository user2Repository;

    @Test
    public void test() {
        User2 user = user2Repository.save(new User2());
        user.getWeights().put(System.currentTimeMillis(), 12.0);
        user2Repository.save(user);
    }
}
