package be.intecbrussel.healthy_goal.api;

import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser (User user) {
        userService.addUser(user);
    }
}
