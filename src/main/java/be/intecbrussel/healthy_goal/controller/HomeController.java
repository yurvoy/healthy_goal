package be.intecbrussel.healthy_goal.controller;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.Advice;
import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.OAuth2Service;
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
    private OAuth2Service oAuth2Service;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping({"","/", "/home"})
    public String home(Principal principal, Model model) {
        User user = userDAO.findByEmail(principal.getName());
        Advice advice = new Advice();

        model.addAttribute("user", user);
        model.addAttribute("advice", advice);

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
        User user = userDAO.findByEmail(principal.getName());

        user.setHeight(userForm.getHeight());
        user.setCurrentWeight(userForm.getCurrentWeight());

        userDAO.save(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/deleteValue", method = RequestMethod.POST)
    public String deleteValue(Principal principal, Long key) {
        User user = userDAO.findByEmail(principal.getName());

        log.info("Removing date {} from user {}", key, user.getId());

        user.deleteValueByKey(key);
        user.setLastAddedValue();

        userDAO.save(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/clearData", method = RequestMethod.POST)
    public String clearData(Principal principal) {
        User user = userDAO.findByEmail(principal.getName());

        user.clearWeights();

        userDAO.save(user);

        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Principal principal, @PathVariable("id") Long id) {
        User user = userDAO.findByEmail(principal.getName());

        userDAO.deleteById(id);

        return "login";
    }
}
