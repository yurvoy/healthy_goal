package be.intecbrussel.healthy_goal.controller;

import be.intecbrussel.healthy_goal.dao.UserDAO;
import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.SocialAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HomeController implements ErrorController {

    @Autowired
    private SocialAuthService authService;
    @Autowired
    private UserDAO userDAO;

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    //TODO Fix thymeleaf send always first index
    @RequestMapping(value = "/deleteValue", method = RequestMethod.POST)
    public String deleteValue(Principal principal, Long key, ModelMap modelMap) {
        User user = authService.extractUserFromAuthInfo(principal);

        logger.info("Removing date {} from user {}", key, user.getId());

        user.deleteValueByKey(key);

        userDAO.save(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/clearData", method = RequestMethod.POST)
    public String clearData(Principal principal, ModelMap modelMap) {
        User user = authService.extractUserFromAuthInfo(principal);

        user.clearWeights();

        userDAO.save(user);

        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Principal principal, @PathVariable("id") String id, ModelMap modelMap) {
        User user = authService.extractUserFromAuthInfo(principal);

        userDAO.deleteById(id);

        return "login";
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
