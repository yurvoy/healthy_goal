package be.intecbrussel.healthy_goal.controller;

import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.SocialAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private SocialAuthService authService;

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

    @RequestMapping(value = "/setWeight", method = RequestMethod.POST)
    public String setWeight(Principal principal, Model model, String value) {
        double weight;
        try {
            weight = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return "wrong value";
        }
        User user = authService.extractUserFromAuthInfo(principal);

        user.setCurrentWeight(weight);

        return "Current weight changed";
    }
}
