package com.hassan.PhotoApp.UsersMicroService.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSec extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users/**").permitAll().and().addFilter(getAuthFilter());
        http.headers().frameOptions().disable();
    }
    private AuthFilter getAuthFilter() throws Exception{
        AuthFilter filter = new AuthFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
