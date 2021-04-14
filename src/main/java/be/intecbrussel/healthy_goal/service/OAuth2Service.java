package be.intecbrussel.healthy_goal.service;

import be.intecbrussel.healthy_goal.config.FacebookOAuth2User;
import be.intecbrussel.healthy_goal.config.GithubOAuth2User;
import be.intecbrussel.healthy_goal.config.GoogleOAuth2User;
import be.intecbrussel.healthy_goal.model.AuthProvider;
import be.intecbrussel.healthy_goal.model.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class OAuth2Service extends DefaultOAuth2UserService {

    private final UserService userService;

    public OAuth2Service(UserService userService) {
        this.userService = userService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        GithubOAuth2User githubOAuth2User = new GithubOAuth2User(oAuth2User);
        GoogleOAuth2User googleOAuth2User = new GoogleOAuth2User(oAuth2User);
        FacebookOAuth2User facebookOAuth2User = new FacebookOAuth2User(oAuth2User);

        String id;
        String email;
        String name;
        AuthProvider provider;
        String picture;

        if (oAuth2User.getAttribute("name") == null) {
            id = githubOAuth2User.getId();
            email = githubOAuth2User.getName();
            name = githubOAuth2User.getFullName();
            picture = githubOAuth2User.getPicture();
            provider = AuthProvider.GITHUB;
        } else if (oAuth2User.getAttribute("access_token") == null) {
            id = googleOAuth2User.getId();
            email = googleOAuth2User.getName();
            name = googleOAuth2User.getFullName();
            picture = googleOAuth2User.getPicture();
            provider = AuthProvider.GOOGLE;
        } else {
            id = facebookOAuth2User.getId();
            email = facebookOAuth2User.getName();
            name = facebookOAuth2User.getFullName();
            picture = null;
            provider = AuthProvider.FACEBOOK;
        }

        User user = userService.findByEmail(email);

        if(user == null) {
            userService.createNewOAuth2User(email, id, name, picture, provider);
        } else {
            userService.updateOAuth2User(user, name, picture, provider);
        }

        if (oAuth2User.getAttribute("name") == null) {
            return githubOAuth2User;
        }
        return googleOAuth2User;
    }

}
