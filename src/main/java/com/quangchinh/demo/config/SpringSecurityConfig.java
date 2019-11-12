package com.quangchinh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                // User controller
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/users/**").hasRole("MEMBER")
                .antMatchers(HttpMethod.POST,"/users").hasAnyRole("ADMIN","MEMBER")
                .antMatchers(HttpMethod.DELETE,"/users").hasAnyRole("ADMIN","MEMBER")
                .antMatchers(HttpMethod.PUT,"/users").hasAnyRole("ADMIN","MEMBER")
                //News Controller
                .antMatchers("/news").permitAll()
                .antMatchers(HttpMethod.POST,"/news").hasAnyRole("ADMIN","MEMBER")
                .antMatchers(HttpMethod.DELETE,"/news/**").hasAnyRole("ADMIN","MEMBER")

                .antMatchers(HttpMethod.PUT,"/news").hasAnyRole("ADMIN","MEMBER")
                // Disable form login
                .and().csrf().disable()
                .formLogin()
                .and()
                .logout();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}