package org.hacklist.config;

import org.hacklist.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Neil Alishev
 */
@Configuration
@EnableWebSecurity
@ComponentScan("org.hacklist.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;
    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider, AccessDeniedHandler accessDeniedHandler) {
        this.authProvider = authProvider;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/sign_in", "/admin/sign_up").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN");

        http.csrf().disable()
                .formLogin()
                .loginPage("/admin/sign_in")
                .loginProcessingUrl("/login/process")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/hacks", true)
                .failureUrl("/admin/sign_in?error=true")
                .and()
                .logout().logoutSuccessUrl("/admin/sign_in")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

}