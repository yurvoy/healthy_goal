package be.intecbrussel.healthy_goal.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class GithubOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;

    public GithubOAuth2User(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    public String getId(){
        return oAuth2User.getAttribute("login");
    }

    // principal.getName() must return email address, else return fake address
    @Override
    public String getName() {
        if (oAuth2User.getAttribute("email") != null) {
            return oAuth2User.getAttribute("email");
        }
        return oAuth2User.getAttribute("login") + "@github.com";
    }

    public String getFullName(){
        return oAuth2User.getAttribute("login");
    }

    public String getPicture() {
        return oAuth2User.getAttribute("avatar_url");
    }

}