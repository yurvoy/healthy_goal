package be.intecbrussel.healthy_goal.controller;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.SocialAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private SocialAuthService authService;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/")
    public String home(Principal principal, Model model) {
        User user = authService.extractUserFromAuthInfo(principal);

        model.addAttribute("user", user);

        return "home";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/setter", method = RequestMethod.POST)
    public String setter(Principal principal, @ModelAttribute("user") User userForm) {
        User user = authService.extractUserFromAuthInfo(principal);

        user.setHeight(userForm.getHeight());
        user.setCurrentWeight(userForm.getCurrentWeight());

        userDAO.save(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/deleteValue", method = RequestMethod.POST)
    public String deleteValue(Principal principal, Long key) {
        User user = authService.extractUserFromAuthInfo(principal);

        log.info("Removing date {} from user {}", key, user.getId());

        user.deleteValueByKey(key);
        user.setLastAddedValue();

        log.debug("test");
        userDAO.save(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/clearData", method = RequestMethod.POST)
    public String clearData(Principal principal) {
        User user = authService.extractUserFromAuthInfo(principal);

        user.clearWeights();

        userDAO.save(user);

        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Principal principal, @PathVariable("id") String id) {
        User user = authService.extractUserFromAuthInfo(principal);

        userDAO.deleteById(id);

        return "login";
    }
}
