package be.intecbrussel.healthy_goal.controller;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.SocialAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController implements ErrorController {

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
    public String setter(Principal principal, @ModelAttribute("user") User userForm, ModelMap modelMap) {
        User user = authService.extractUserFromAuthInfo(principal);

        user.setHeight(userForm.getHeight());
        user.setCurrentWeight(userForm.getCurrentWeight());

        userDAO.save(user);

        return "redirect:/";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "customError";
    }

    @Override
    public String getErrorPath() {
        return "/some-non-existing-path";
    }
}
