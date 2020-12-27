package be.intecbrussel.healthy_goal.service.implementation;

import be.intecbrussel.healthy_goal.model.User;
import be.intecbrussel.healthy_goal.service.framework.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LocalUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public LocalUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Email address {" + email + "} not found!");
        }
        return user;
    }
}
