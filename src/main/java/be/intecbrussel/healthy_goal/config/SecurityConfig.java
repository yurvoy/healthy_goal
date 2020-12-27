package be.intecbrussel.healthy_goal.config;

import be.intecbrussel.healthy_goal.config.oauth.OAuth2UserService;
import be.intecbrussel.healthy_goal.config.security.LocalSuccessHandler;
import be.intecbrussel.healthy_goal.config.security.OAuthSuccessHandler;
import be.intecbrussel.healthy_goal.service.implementation.LocalUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final LocalSuccessHandler localSuccessHandler;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final LocalUserDetailsService localUserDetailsService;
    private final OAuth2UserService oAuth2UserService;

    public SecurityConfig(LocalSuccessHandler localSuccessHandler, OAuthSuccessHandler oAuthSuccessHandler, LocalUserDetailsService localUserDetailsService, OAuth2UserService oAuth2UserService) {
        this.localSuccessHandler = localSuccessHandler;
        this.oAuthSuccessHandler = oAuthSuccessHandler;
        this.localUserDetailsService = localUserDetailsService;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").successHandler(localSuccessHandler)
                .and()
                .headers(headers -> headers.addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'")))
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint("/login"))
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .loginPage("/login").successHandler(oAuthSuccessHandler)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(localUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
