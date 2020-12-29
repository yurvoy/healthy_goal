package be.intecbrussel.healthy_goal.service;

import be.intecbrussel.healthy_goal.model.AuthProvider;
import be.intecbrussel.healthy_goal.model.User;
import lombok.NonNull;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
public class SocialAuthService {
    private static final String GOOGLE_ID_FIELD_NAME = "sub";
    private static final String AUTH_DETAILS_NAME_PARAM = "name";
    private static final String AUTH_DETAILS_EMAIL_PARAM = "email";

    public User extractUserFromAuthInfo(@NonNull Principal principal) {
        if (principal instanceof OAuth2Authentication) {
            return extractExternalUser(principal);
        }
        return null;
    }

    private User extractExternalUser(@NonNull Principal principal) {
        OAuth2Authentication oAuth = (OAuth2Authentication) principal;
        Map<String, String> details = (Map<String, String>) oAuth.getUserAuthentication().getDetails();

        AuthProvider authProvider;
        String extIdStr;
        if (isGoogle(details)) {
            authProvider = AuthProvider.GOOGLE;
            extIdStr = details.get(GOOGLE_ID_FIELD_NAME);
        } else {
            authProvider = AuthProvider.FACEBOOK;
            extIdStr = (String) oAuth.getUserAuthentication().getPrincipal();
        }
        return new User(extIdStr, details.get(AUTH_DETAILS_NAME_PARAM), details.get(AUTH_DETAILS_EMAIL_PARAM), authProvider);
    }

    private boolean isGoogle(@NonNull Map<String, String> details) {
        return details.containsKey(GOOGLE_ID_FIELD_NAME);
    }
}
