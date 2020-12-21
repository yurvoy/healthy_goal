package be.intecbrussel.healthy_goal.api;

import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById (@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping
    public void updateUser(@PathVariable("id")UUID id, @RequestBody double newWeight){
        userService.updateUser(id, newWeight);
    }
}
