package be.intecbrussel.healthy_goal.api;

import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser (@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }
}
