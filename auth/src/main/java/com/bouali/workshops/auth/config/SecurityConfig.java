package com.bouali.workshops.auth.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // telling spring how to fetch users here from inmemory database to authenticate
    // now spring knows which authentication manager he needs to use
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("user")
                .roles("USER");
    }

    //we r telling spring how to manage our http security
    // for this request or for this matchers just authorize them otherwise it needs to be authenticated
    // .authorizeRequests() here we can precise the requests we want ot authorize or we dont want to authorize
    // .antMatchers() here we can pass list of patterns
    // permitAll() i want to permit all the access -> if user wants to access this end point "/api/v*/public/**" then no login required
    // "/api/v*/public/**" access without authentication
    // .anyRequest() means any other request or any other path we need authentication for that
    // here i want ot use form login based authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v*/public/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // never use this in production environment
        // its just a class implementing password encoder interface
        // we can use it for testing purposes
        return NoOpPasswordEncoder.getInstance();
    }
}
