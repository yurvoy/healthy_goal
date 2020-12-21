package be.intecbrussel.healthy_goal.api;

import be.intecbrussel.healthy_goal.dao.UserDao;
import be.intecbrussel.healthy_goal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/user")
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping
    public void addUser (@NonNull @RequestBody User user) {
        userDao.save(user);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userDao.findById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById (@PathVariable("id") Long id) {
        userDao.deleteById(id);
    }

    @PutMapping
    public void updateUser (@PathVariable("id")Long id, @NonNull @RequestBody double newWeight){
        userDao.findById(id)
                .orElse(null);
    }
}
