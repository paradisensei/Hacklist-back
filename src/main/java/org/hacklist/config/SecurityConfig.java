package org.hacklist.config;

import org.hacklist.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Neil Alishev
 */
@EnableWebSecurity
@Configuration
@ComponentScan("org.hacklist.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/sign_in").access("permitAll")
                .antMatchers("/admin/sign_up").access("permitAll")
                .antMatchers("/**").access("isAuthenticated() and hasRole('ROLE_ENABLED')");

        http.formLogin()
                .defaultSuccessUrl("/admin/hacks", true)
                .loginPage("/admin/sign_in")
                .loginProcessingUrl("/sign_in/process")
                .failureForwardUrl("/admin/sign_in?error=true")
                .usernameParameter("email")
                .passwordParameter("password");
    }
}